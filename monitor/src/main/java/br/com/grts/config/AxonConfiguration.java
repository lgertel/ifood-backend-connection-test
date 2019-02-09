package br.com.grts.config;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.java.SimpleEventScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class AxonConfiguration {

  @Autowired
  private AxonConfiguration axonConfiguration;
  @Autowired
  private EventBus eventBus;

  @Bean
  public EventScheduler eventScheduler() {
    return new SimpleEventScheduler(Executors.newScheduledThreadPool(5), eventBus);
  }
}
