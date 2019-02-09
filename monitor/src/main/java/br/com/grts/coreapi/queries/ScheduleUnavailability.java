package br.com.grts.ifood.coreapi.queries;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class ScheduleUnavailability {

  private final String restaurantId;
  private LocalDateTime start;
  private LocalDateTime end;
  private LocalDateTime createdAt;

  public ScheduleUnavailability(String restaurantId, LocalDateTime start, LocalDateTime end, LocalDateTime createdAt) {
    this.restaurantId = restaurantId;
    this.start = start;
    this.end = end;
    this.createdAt = createdAt;
  }
}
