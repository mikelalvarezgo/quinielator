package com.mikelalvarezgo.quinielator.shared.domain.model

import cats.data.Validated
import com.mikelalvarezgo.quinielator.shared.domain.error.{InvalidSeason, ValidationErrorException}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation

case class Season(value: String)

object Season {

  private def isValid(value: String): Boolean = {
    val splitValue = value.split("/")
    if (splitValue.length == 2) {
      val year1 = Option(splitValue.head.toInt)
      val year2 = Option(splitValue.last.toInt)
      year1.flatMap(y1 => year2.map(y2 => (y2 - y1) == 1)).getOrElse(false)
    } else false
  }
  def unsafe(value: String): Season =
    ValidationErrorException.getOrThrow(fromString(value))

  def fromString(value: String): Validation[Season] =
    Validated.condNel(
      isValid(value),
      Season(value),
      InvalidSeason(value)
    )
}
