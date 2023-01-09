package com.smilebat.learntribe.learntribeclients;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * Configuration for content type form xml processing
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
public class CoreFeignConfiguration {
  @Autowired private ObjectFactory<HttpMessageConverters> messageConverters;

  /**
   * Bean for form encoder.
   *
   * @return the {@link Encoder}.
   */
  @Bean
  @Primary
  @Scope(SCOPE_PROTOTYPE)
  Encoder feignFormEncoder() {
    return new FormEncoder(new SpringEncoder(this.messageConverters));
  }
}
