package com.mikelalvarezgo.quinielator.shared.domain.model

import java.util.UUID

import cats.data.Validated
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.error.{InvalidCalendarId, ValidationErrorException}

final case class CalendarId(value: UUID) extends Id(value)

object CalendarId {
  def unsafe(value: String): CalendarId =
    ValidationErrorException.getOrThrow(fromString(value))

  def create: CalendarId = CalendarId(UUID.randomUUID())

  def fromString(value: String): Validation[CalendarId] =
    Validated.condNel(
      Id.isValid(value),
      CalendarId(UUID.fromString(value)),
      InvalidCalendarId(value)
    )
}
