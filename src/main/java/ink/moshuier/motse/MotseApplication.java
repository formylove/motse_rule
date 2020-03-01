package ink.moshuier.motse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories("ink.moshuier.motse.repository")
@EntityScan("ink.moshuier.motse.entity")
public class MotseApplication {

  @Bean
  public ObjectMapper configureHttpMessageCodecs() {
    return Jackson2ObjectMapperBuilder.json()
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .modules(new JavaTimeModule())
            .build();
  }

  public static void main(String[] args) {
    SpringApplication.run(MotseApplication.class, args);
  }

}
