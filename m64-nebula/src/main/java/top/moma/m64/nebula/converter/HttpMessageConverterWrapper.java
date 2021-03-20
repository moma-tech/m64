package top.moma.m64.nebula.converter;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import top.moma.m64.core.helper.json.JsonHelper;

import java.util.function.Consumer;

/**
 * HttpMessageConverterWrapper
 *
 * <p>Warp specified Http Message Converter
 *
 * @author Ivan
 * @version 1.0 Created by Ivan at 2021/3/20.
 */
public class HttpMessageConverterWrapper {
  public static Consumer<HttpMessageConverter<?>> messageConverterConsumer() {
    return httpMessageConverter -> {
      /* Jackson */
      if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
            (MappingJackson2HttpMessageConverter) httpMessageConverter;
        mappingJackson2HttpMessageConverter.setObjectMapper(
            JsonHelper.getObjectMapper(mappingJackson2HttpMessageConverter.getObjectMapper()));
      }
    };
  }
}
