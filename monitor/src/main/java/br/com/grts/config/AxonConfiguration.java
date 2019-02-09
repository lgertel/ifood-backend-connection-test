package br.com.grts.config;

import org.axonframework.spring.eventhandling.scheduling.java.SimpleEventSchedulerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {

  @Bean
  public SimpleEventSchedulerFactoryBean simpleEventSchedulerFactoryBean() {
    return new SimpleEventSchedulerFactoryBean();
  }
}
