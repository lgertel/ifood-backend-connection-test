package br.com.grts.ifood.coreapi.queries;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Restaurant {

  private final String restaurantId;
  private final String name;

  public Restaurant(String restaurantId, String name) {
    this.restaurantId = restaurantId;
    this.name = name;
  }
}
