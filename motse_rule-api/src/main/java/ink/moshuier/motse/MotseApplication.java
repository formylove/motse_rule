package ink.moshuier.motse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories("ink.moshuier.motse")
@EntityScan("ink.moshuier.motse.model")
public class MotseApplication {

  public static void main(String[] args) {
    SpringApplication.run(MotseApplication.class, args);
  }
}
