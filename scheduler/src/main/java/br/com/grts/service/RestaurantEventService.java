package br.com.grts.service;

import br.com.grts.coreapi.RestaurantCreatedEvent;
import br.com.grts.model.Restaurant;
import br.com.grts.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@ProcessingGroup("amqpEvents")
@AllArgsConstructor
@Slf4j
public class RestaurantEventService {

  private final RestaurantRepository repository;

  @EventHandler
  public void on(RestaurantCreatedEvent event) {
    Restaurant restaurant = new Restaurant();
    restaurant.setAxonId(event.getRestaurantId());
    restaurant.setName(event.getName());
    restaurant.setUnavailabilities(Collections.emptySet());

    repository.save(restaurant);
    log.info("A restaurant was added! {}", restaurant);
  }
}
