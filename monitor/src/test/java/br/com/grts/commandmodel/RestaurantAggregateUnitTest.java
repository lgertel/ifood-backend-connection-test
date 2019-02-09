package br.com.grts.commandmodel;

import br.com.grts.coreapi.CreateRestaurantCommand;
import br.com.grts.coreapi.RestaurantCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class RestaurantAggregateUnitTest {

  private FixtureConfiguration<RestaurantAggregate> fixture;

  @Before
  public void setUp() {
    fixture = new AggregateTestFixture<>(RestaurantAggregate.class);
  }

  @Test
  public void giveNoPriorActivity_whenCreateRestaurantCommand_thenShouldPublishRestaurantCreatedEvent() {
    String restaurantId = UUID.randomUUID().toString();
    String name = "BurgerKing";

    fixture.givenNoPriorActivity()
        .when(new CreateRestaurantCommand(restaurantId, name))
        .expectEvents(new RestaurantCreatedEvent(restaurantId, name));
  }
}
