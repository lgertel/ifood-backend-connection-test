package br.com.grts.commandmodel;

import br.com.grts.coreapi.CreateRestaurantCommand;
import br.com.grts.coreapi.RestaurantCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@NoArgsConstructor
public class RestaurantAggregate {

  @AggregateIdentifier
  private String restaurantId;
  private String name;

  @CommandHandler
  public RestaurantAggregate(CreateRestaurantCommand cmd) {
    log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    apply(new RestaurantCreatedEvent(
        cmd.getRestaurantId(),
        cmd.getName()
    ));
  }

  @EventHandler
  public void on(RestaurantCreatedEvent event) {
    log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
    this.restaurantId = event.getRestaurantId();
    this.name = event.getName();
  }
}
