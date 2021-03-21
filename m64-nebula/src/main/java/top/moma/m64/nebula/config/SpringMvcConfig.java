package top.moma.m64.nebula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.moma.m64.nebula.converter.HttpMessageConverterWrapper;
import top.moma.m64.nebula.converter.ParameterConverters;

import java.util.List;

/**
 * SpringMvc
 *
 * <p>Spring Mvc Configuration
 *
 * @author Ivan
 * @version 1.0 Created by Ivan at 2021/3/21.
 */
public class SpringMvcConfig implements WebMvcConfigurer {
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.forEach(HttpMessageConverterWrapper.messageConverterConsumer());
  }

  @Bean
  public ParameterConverters parameterConverters() {
    return new ParameterConverters();
  }
}
