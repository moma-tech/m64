package top.moma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * M64Application
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@SpringBootApplication
@EnableJpaRepositories
public class M64Application {
  public static void main(String[] args) {
    SpringApplication.run(M64Application.class, args);
  }
}
