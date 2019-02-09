package br.com.grts.coreapi;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class UnavailabilityScheduledEvent {
  private String scheduleUnavailabilityId;
  private String restaurantId;
  private LocalDateTime start;
  private LocalDateTime end;
  private LocalDateTime createdAt;
}
