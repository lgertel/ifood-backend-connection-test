package br.com.grts.querymodel;

import br.com.grts.coreapi.FindAllScheduleUnavailabilitiesQuery;
import br.com.grts.coreapi.UnavailabilityScheduledEvent;
import br.com.grts.coreapi.queries.ScheduleUnavailability;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScheduleUnavailabilityEventHandler {

  private List<ScheduleUnavailability> scheduledUnavailabilities = new ArrayList<>();

  @EventHandler
  public void on(UnavailabilityScheduledEvent event) {
    log.info("Handling {} query: {}", event.getClass().getSimpleName(), event);
    scheduledUnavailabilities.add(new ScheduleUnavailability(event.getRestaurantId(), event.getStart(), event.getEnd(), event.getCreatedAt()));
  }

  @QueryHandler
  public List<ScheduleUnavailability> handle(FindAllScheduleUnavailabilitiesQuery query) {
    return scheduledUnavailabilities;
  }
}
