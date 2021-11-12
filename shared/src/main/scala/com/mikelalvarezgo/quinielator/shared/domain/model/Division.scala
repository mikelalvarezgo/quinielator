package com.mikelalvarezgo.quinielator.shared.domain.model

import ca.mrvisser.sealerate
import cats.data.Validated.{Invalid, Valid}
import com.mikelalvarezgo.quinielator.shared.domain.error.{InvalidDivision, ValidationErrorException}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation

sealed abstract class Division(val value: String)

object Division {

  private lazy val allByValue: Map[String, Division] = all.map(ts => ts.value -> ts).toMap

  def all: Set[Division] = sealerate.values[Division]

  def unsafe(value: String): Division = ValidationErrorException.getOrThrow(fromString(value))

  def fromString(value: String): Validation[Division] =
    allByValue.get(value).map(Valid.apply).getOrElse(Invalid(InvalidDivision(value)).toValidatedNel)

}

final case object Primera extends Division("primera")
final case object Segunda extends Division("segunda")
