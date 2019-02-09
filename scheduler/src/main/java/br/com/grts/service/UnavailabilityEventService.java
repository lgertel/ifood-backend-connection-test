package br.com.grts.service;

import br.com.grts.coreapi.UnavailabilityScheduledEvent;
import br.com.grts.model.Restaurant;
import br.com.grts.model.Unavailability;
import br.com.grts.repository.RestaurantRepository;
import br.com.grts.repository.UnavailabilityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@ProcessingGroup("amqpEvents")
@AllArgsConstructor
@Slf4j
@Transactional
public class UnavailabilityEventService {

  private final EntityManager entityManager;

  private final RestaurantRepository restaurantRepository;
  private final UnavailabilityRepository unavailabilityRepository;

  @EventHandler
  public void on(UnavailabilityScheduledEvent event) {
    Restaurant restaurant = restaurantRepository.findByAxonId(event.getRestaurantId());

    Unavailability unavailability = new Unavailability();
    unavailability.setAxonId(event.getRestaurantId());
    unavailability.setStart(event.getStart());
    unavailability.setEnd(event.getEnd());
    unavailability.setCreatedAt(event.getCreatedAt());
    unavailabilityRepository.save(unavailability);

    restaurant.getUnavailabilities().add(unavailability);
    restaurantRepository.save(restaurant);

    log.info("A unavailability was added! {}", unavailability);
  }
}
