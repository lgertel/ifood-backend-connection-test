package br.com.grts.ifood.coreapi.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class CreateRestaurantCommand {

  @TargetAggregateIdentifier
  private String restaurantId;
  private String name;
}
