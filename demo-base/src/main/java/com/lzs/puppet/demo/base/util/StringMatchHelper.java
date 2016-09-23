/**
 * Project Name: demo-base
 * File Name: StringMatchUtil.java
 * Package Name: com.lzs.puppet.demo.base.util
 * Describe: TODO
 * Date: 2016年9月18日下午5:20:12
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.base.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: StringMatchUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年9月18日 下午5:20:12 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringMatchHelper {

	private Set<String> set = new HashSet<String>();
	private Set<String> baseset = new HashSet<String>();
	
	public StringMatchHelper(Set<String> base){
		if(base != null && !base.isEmpty()){
			baseset = base;
		}
	}
	
	public boolean match(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		if(set.contains(str)){
			return true;
		}
		if(baseset.contains(str)){
			set.add(str);
			return true;
		}
		Iterator<String> it = baseset.iterator();
		String s = null;
		while(it.hasNext()){
			s = it.next();
			if(s!=null&&str.matches(s)){
				set.add(str);
				return true;
			}
		}
		return false;
	}
}

	