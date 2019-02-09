package br.com.grts.ifood.coreapi.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class ScheduleUnavailabilityCommand {

  @TargetAggregateIdentifier
  private String scheduleUnavailabilityId;
  private String restaurantId;
  private LocalDateTime start;
  private LocalDateTime end;
  private LocalDateTime createdAt;
}
