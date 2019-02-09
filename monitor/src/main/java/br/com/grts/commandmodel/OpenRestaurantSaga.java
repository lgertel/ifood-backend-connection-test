package br.com.grts.commandmodel;

import br.com.grts.coreapi.RestaurantToggledOfflineEvent;
import br.com.grts.coreapi.RestaurantToggledOfflineSagaEvent;
import br.com.grts.coreapi.RestaurantToggledOnlineEvent;
import br.com.grts.coreapi.ToggleRestaurantOfflineCommand;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.java.SimpleEventScheduler;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.Duration;

@Saga
@Slf4j
public class OpenRestaurantSaga implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(OpenRestaurantSaga.class);

  @Autowired @Setter
  private transient CommandGateway commandGateway;

  @Autowired @Setter
  transient SimpleEventScheduler eventScheduler;

  String restaurantId;

  @StartSaga
  @SagaEventHandler(associationProperty = "restaurantId")
  public void handle(RestaurantToggledOnlineEvent event) {
    log.info("Handling {} saga: {}", event.getClass().getSimpleName(), event);
    restaurantId = event.getRestaurantId();

    commandGateway.send(new ToggleRestaurantOfflineCommand(restaurantId));
    eventScheduler.schedule(Duration.ofSeconds(10), new RestaurantToggledOfflineEvent(restaurantId));
  }

  @EndSaga
  @SagaEventHandler(associationProperty = "restaurantId")
  public void handle(RestaurantToggledOfflineEvent event) {
    log.info("Ending {} saga: {}", event.getClass().getSimpleName(), event);
  }
}
