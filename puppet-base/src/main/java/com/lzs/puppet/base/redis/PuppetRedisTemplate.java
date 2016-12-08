/**
 * Project Name: puppet-base
 * File Name: PuppetRedisTemplate.java
 * Package Name: com.lzs.puppet.base.redis
 * Describe: TODO
 * Date: 2016年12月7日上午9:10:00
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.redis;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * ClassName: PuppetRedisTemplate <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年12月7日 上午9:10:00 <br/>
 * @author: hzlizhaosheng
 * @version
 * @param String
 * @param <V>
 * @since JDK 1.6
 * @see
 */
public class PuppetRedisTemplate extends RedisTemplate<String, Object> {

	@Autowired 
	private PuppetRedisProperties puppetRedisProperties;
	
	/**
	 * 重命名key，放到事务中。
	 * 注：所有要使用redis事务，请遵循类型的方式进行，不能直接使用multi
	 * http://www.opensourcelab.cn/2014/09/23/spring-data-redis%E4%B8%AD%E7%9A%84%E5%9D%91%E5%92%8C%E8%AF%AF%E5%8C%BA/
	 * redistemplate的multi和exec方法，都是新产生连接，而非使用本来的连接，
	 * 直接使用multi会导致异常（因为新连接中，直接执行退出同步，系统肯定会去找是从哪儿开始同步的，这一找，发现没有开始同步的命令，就会抛出异常了）
	 * @see org.springframework.data.redis.core.RedisTemplate#rename()
	 */
	public void rename(String key, String newKey) {
		execute(new SessionCallback<Object>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Object execute(RedisOperations operations) throws DataAccessException {
				do {
					operations.multi();
					operations.rename(key, newKey);
				} while (operations.exec() == null);
				return null;
			}
		});
	}
	
	/**
     * 获取分布式锁
     * 
     * @param lockName
     *            竞争获取锁key
     * @param acquireTimeoutInMS
     *            获取锁超时时间
     * @param lockExpire
     *            锁的超时时间,单位毫秒
     * @return 获取锁标识
     */
    public String acquireLock(String lockName, long acquireTimeoutInMS, long lockExpire) {
        String retIdentifier = null;

        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:" + lockName;

        long end = System.currentTimeMillis() + acquireTimeoutInMS;
        while (System.currentTimeMillis() < end) {
        	// 将lockKey变量设置为identifier，仅当lockKey为null才能设置成功返回1
        	if(opsForValue().setIfAbsent(lockKey, identifier)){
        		expire(lockKey, lockExpire, TimeUnit.MILLISECONDS);
        		retIdentifier = identifier;
                break;
        	}
            try {
            	 // 短暂休眠，nano避免出现活锁
                Thread.sleep(5);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        // 获取不到锁，检查锁的超时时间是否是永久，若为永久，则重新设置超时时间
        if (retIdentifier == null && getExpire(lockKey) == -1) {
        	expire(lockKey, lockExpire, TimeUnit.MILLISECONDS);
        }
       
        return retIdentifier;
    }
    /**
     * 获取分布式锁，仅尝试一次，若要进行多次尝试，请使用acquireLock
     * 
     * @param lockName
     *            竞争获取锁key
     * @param lockExpire
     *            锁的超时时间
     * @return 获取锁标识
     */
	public String getLock(String lockName, long lockExpire) {
        String retIdentifier = null;
        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:" + lockName;

     // 将lockKey变量设置为identifier，仅当lockKey为null才能设置成功返回1
    	if(opsForValue().setIfAbsent(lockKey, identifier)){
    		expire(lockKey, lockExpire, TimeUnit.MILLISECONDS);
    		retIdentifier = identifier;
    	}
    	// 获取不到锁，检查锁的超时时间是否是永久，若为永久，则重新设置超时时间
        if (retIdentifier == null && getExpire(lockKey) == -1) {
        	expire(lockKey, lockExpire, TimeUnit.MILLISECONDS);
        }
        return retIdentifier;
    }
	
    /**
     * 释放锁
     * @param lockName 竞争获取锁key
     * @param identifier 释放锁标识
     * @return
     */
	public boolean releaseLock(String lockName, String identifier) {
        String lockKey = "lock:" + lockName;
        if (identifier.equals(opsForValue().get(lockKey))) {
        	 if (puppetRedisProperties.isTransactionSupport() ) {
                 watch(lockKey);
                 deleteT(lockKey);
                 unwatch();
                 return true;
             }
        	 else{
        		 delete(lockKey);
        		 return true;
        	 }
   	 	}
        return false;
    }

	/**
	 * 
	 * 删除指定键（事务） 
	 *
	 * @author hzlizhaosheng
	 * @param lockKey
	 * @return
	 */
	public List<Object> deleteT(String key) {
		return execute(new SessionCallback<List<Object>>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public List<Object> execute(RedisOperations operations) throws DataAccessException {
				List<Object> results = null;
				do {
					operations.multi();
					operations.delete(key);
					results = operations.exec();
				} while (results == null);
				return results;
			}
		});
	}
}

	