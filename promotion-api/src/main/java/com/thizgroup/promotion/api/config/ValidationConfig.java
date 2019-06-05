package com.thizgroup.promotion.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.validation.Validator;

/**
 * @author : Sarah Xu
 * @date : 2019-06-04
 */
@Configuration
public class ValidationConfig extends WebMvcConfigurationSupport {
  @Autowired private MessageSource messageSource;

  @Bean
  public Validator validator() {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(messageSource);
    return validator;
  }
}
