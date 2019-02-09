package br.com.grts.ifood.coreapi.queries;

import lombok.Value;

@Value
public class FindAllScheduleUnavailabilitiesByRestaurantQuery {

  private final String restaurantId;
}
