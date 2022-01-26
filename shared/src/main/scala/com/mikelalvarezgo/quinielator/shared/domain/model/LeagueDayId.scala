package com.mikelalvarezgo.quinielator.shared.domain.model

import cats.data.Validated
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.error.{InvalidLeagueDayId, ValidationErrorException}

import java.util.UUID

final case class LeagueDayId(value: UUID) extends Id(value)

object LeagueDayId {
  def unsafe(value: String): LeagueDayId =
    ValidationErrorException.getOrThrow(fromString(value))

  def create: LeagueDayId = LeagueDayId(UUID.randomUUID())

  def fromString(value: String): Validation[LeagueDayId] =
    Validated.condNel(
      Id.isValid(value),
      LeagueDayId(UUID.fromString(value)),
      InvalidLeagueDayId(value)
    )
}
