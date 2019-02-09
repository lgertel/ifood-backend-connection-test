package br.com.grts.ifood.querymodel;

import br.com.grts.ifood.coreapi.events.RestaurantCreatedEvent;
import br.com.grts.ifood.coreapi.queries.FindAllRestaurantsQuery;
import br.com.grts.ifood.coreapi.queries.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RestaurantEventHandler {

  private List<Restaurant> restaurants = new ArrayList<>();

  @EventHandler
  public void on(RestaurantCreatedEvent event) {
    log.info("Handling {} query: {}", event.getClass().getSimpleName(), event);
    restaurants.add(new Restaurant(event.getRestaurantId(), event.getName()));
  }

  @QueryHandler
  public List<Restaurant> handle(FindAllRestaurantsQuery query) {
    return restaurants;
  }
}
