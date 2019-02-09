package br.com.grts.coreapi.queries;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
