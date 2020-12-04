package top.moma.m64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * M64Application
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 12/4/20.
 */
@SpringBootApplication
@EnableFeignClients
public class M64Application {
  public static void main(String[] args) {
    SpringApplication.run(M64Application.class, args);
  }
}
