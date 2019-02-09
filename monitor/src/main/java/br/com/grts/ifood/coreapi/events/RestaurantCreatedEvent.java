package br.com.grts.ifood.coreapi.events;

import lombok.Value;

@Value
public class RestaurantCreatedEvent {
  private String restaurantId;
  private String name;
}
