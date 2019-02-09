package br.com.grts.commandmodel;

import br.com.grts.coreapi.RestaurantToggledOfflineEvent;
import br.com.grts.coreapi.RestaurantToggledOnlineEvent;
import br.com.grts.coreapi.ToggleRestaurantOfflineCommand;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.Instant;

@Saga
@Slf4j
public class OpenRestaurantSaga implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(OpenRestaurantSaga.class);
  String restaurantId;

  @Autowired @Setter
  private transient CommandGateway commandGateway;

  @Autowired @Setter
  private transient EventScheduler eventScheduler;

  @StartSaga
  @SagaEventHandler(associationProperty = "restaurantId")
  public void handle(RestaurantToggledOnlineEvent event) {
    log.info("Handling {} saga: {}", event.getClass().getSimpleName(), event);
    restaurantId = event.getRestaurantId();

//    commandGateway.send(new ToggleRestaurantOfflineCommand(restaurantId));

    eventScheduler.schedule(
        Instant.now().plusSeconds(10),
        new RestaurantToggledOfflineEvent(event.getRestaurantId())
    );
  }

  @SagaEventHandler(associationProperty = "restaurantId")
  public void on(RestaurantToggledOfflineEvent event) {
    log.info("Handling {} scheduled event: {}", event.getClass().getSimpleName(), event);
  }
}
