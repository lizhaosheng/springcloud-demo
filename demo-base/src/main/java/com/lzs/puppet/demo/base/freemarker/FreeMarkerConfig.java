/**
 * Project Name: demo-base
 * File Name: FreeMarkerConfig.java
 * Package Name: com.lzs.puppet.demo.base.freemarker
 * Describe: TODO
 * Date: 2016年9月21日上午11:33:48
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.base.freemarker;

import java.util.Properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * ClassName: FreeMarkerConfig <br/>
 * Function: freemarker 的配置信息，已经配置bean（会覆盖默认的org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration中生成的同类bean） <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年9月21日 上午11:33:48 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@ConfigurationProperties(prefix = "freemarker")
public class FreeMarkerConfig extends FreeMarkerProperties{

	@Bean
	@ConditionalOnMissingBean(FreeMarkerConfig.class)
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		applyProperties(configurer);
		return configurer;
	}

	private void applyProperties(FreeMarkerConfigurer factory) {
		factory.setTemplateLoaderPaths(getTemplateLoaderPath());
		factory.setPreferFileSystemAccess(isPreferFileSystemAccess());
		factory.setDefaultEncoding(getCharsetName());
		Properties settings = new Properties();
		settings.putAll(getSettings());
		factory.setFreemarkerSettings(settings);
	}
}

	