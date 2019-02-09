package br.com.grts.commandmodel;

import br.com.grts.coreapi.ScheduleUnavailabilityCommand;
import br.com.grts.coreapi.UnavailabilityScheduledEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@NoArgsConstructor
public class ScheduleUnavailabilityAggregate {

  @AggregateIdentifier
  private String scheduleUnavailabilityId;

  private String restaurantId;

  private LocalDateTime start;
  private LocalDateTime end;

  private LocalDateTime createdAt;

  @CommandHandler
  public ScheduleUnavailabilityAggregate(ScheduleUnavailabilityCommand cmd) {
    log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    apply(
        new UnavailabilityScheduledEvent(
            cmd.getScheduleUnavailabilityId(),
            cmd.getRestaurantId(),
            cmd.getStart(),
            cmd.getEnd(),
            LocalDateTime.now()
        )
    );
  }

  @EventHandler
  public void on(UnavailabilityScheduledEvent event) {
    log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
    this.scheduleUnavailabilityId = event.getScheduleUnavailabilityId();
    this.restaurantId =  event.getRestaurantId();
    this.start = event.getStart();
    this.end = event.getEnd();
    this.createdAt = event.getCreatedAt();
  }
}
