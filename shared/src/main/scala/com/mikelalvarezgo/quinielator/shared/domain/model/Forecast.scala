package com.mikelalvarezgo.quinielator.shared.domain.model

import ca.mrvisser.sealerate
import cats.data.Validated.{Invalid, Valid}

import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.error.{InvalidForecast, ValidationErrorException}

sealed abstract class Forecast(val value: String)

object Forecast {

  private lazy val allByValue: Map[String, Forecast] = all.map(ts => ts.value -> ts).toMap

  def all: Set[Forecast] = sealerate.values[Forecast]

  def unsafe(value: String): Forecast = ValidationErrorException.getOrThrow(fromString(value))

  def fromString(value: String): Validation[Forecast] =
    allByValue.get(value).map(Valid.apply).getOrElse(Invalid(InvalidForecast(value)).toValidatedNel)

}

final case object HomeWin    extends Forecast("1")
final case object VisitorWin extends Forecast("2")
final case object Tie        extends Forecast("X")
