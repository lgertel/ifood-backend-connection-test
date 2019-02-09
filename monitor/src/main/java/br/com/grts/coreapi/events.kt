package br.com.grts.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.time.LocalDateTime

data class RestaurantCreatedEvent(
    val restaurantId: String,
    val name: String
)

data class UnavailabilityScheduledEvent(
    val scheduleUnavailabilityId: String,
    val restaurantId: String,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val createdAt: LocalDateTime
)

data class RestaurantToggledOnlineEvent(
    val restaurantId: String,
    val time: LocalDateTime
)

data class RestaurantToggledOfflineSagaEvent(
    val restaurantId: String
)

data class RestaurantToggledOfflineEvent(
    val restaurantId: String
)

