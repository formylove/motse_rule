package com.thizgroup.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BindingRecorderApplication {

  public static void main(String[] args) {
    SpringApplication.run(BindingRecorderApplication.class, args);
  }
}
