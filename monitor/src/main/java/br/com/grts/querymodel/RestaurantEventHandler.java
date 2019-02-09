package br.com.grts.querymodel;

import br.com.grts.coreapi.FindAllRestaurantsQuery;
import br.com.grts.coreapi.RestaurantCreatedEvent;
import br.com.grts.coreapi.queries.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
