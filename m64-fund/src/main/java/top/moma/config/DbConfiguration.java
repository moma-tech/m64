package top.moma.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * DbConfiguration
 *
 * <p>//TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan at 2/25/21.
 */
@SpringBootConfiguration
public class DbConfiguration {
  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.username("moma");
    dataSourceBuilder.password("");
    return dataSourceBuilder.build();
  }
}
