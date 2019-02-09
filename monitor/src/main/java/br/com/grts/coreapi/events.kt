package br.com.grts.coreapi

import java.time.LocalDateTime

data class RestaurantCreatedEvent(
  val restaurantId: String,
  val name: String
)

data class UnavailabilityScheduledEvent (
  val scheduleUnavailabilityId: String,
  val restaurantId: String,
  val start: LocalDateTime,
  val end: LocalDateTime,
  val createdAt: LocalDateTime
)

