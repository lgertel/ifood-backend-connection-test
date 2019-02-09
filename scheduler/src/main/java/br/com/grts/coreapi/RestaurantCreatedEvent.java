package br.com.grts.coreapi;

import lombok.Value;

@Value
public class RestaurantCreatedEvent {
  private String restaurantId;
  private String name;
}
