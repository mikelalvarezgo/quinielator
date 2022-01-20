package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model

import cats.data.Validated.{Invalid, Valid}
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error.InvalidRound
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.error.ValidationErrorException

case class Round(value: Int)

object Round{
  private def isValid: Int => Boolean = value => value > 0 && value < 41
  def unsafe(value: Int): Round = ValidationErrorException.getOrThrow(fromInt(value))

  def fromInt(value: Int): Validation[Round] =
    if(isValid(value)) Valid(Round(value)) else (Invalid(InvalidRound(value)).toValidatedNel)
}
