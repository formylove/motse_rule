package com.thizgroup.promotion.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(
    basePackages = {
      "com.thizgroup.promotion",
      "com.thizgroup.promotion.service",
      "com.thizgroup.promotion.api",
      "com.thizgroup.promotion.dao",
      "com.thizgroup.promotion.model"
    })
@EnableJpaRepositories("com.thizgroup.promotion.dao.repo")
@EntityScan("com.thizgroup.promotion.model")
public class PromotionApplication {

  public static void main(String[] args) {
    SpringApplication.run(PromotionApplication.class, args);
  }
}
