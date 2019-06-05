package com.thizgroup.promotion.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author : Sarah Xu
 * @date : 2019-05-30
 */
@Configuration
public class I18nApp {
  @Bean("messageSource")
  ResourceBundleMessageSource resourceBundleMessageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames(
        new String[] {"locale/messages", "locale/ValidationMessages"}); // 添加资源名称
    messageSource.setDefaultEncoding("utf-8");
    return messageSource;
  }
}
