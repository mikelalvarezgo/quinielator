package com.mikelalvarezgo.quinielator.shared.domain.error

final case class InvalidDivision(value: String) extends ValidationError {
  override def message: String = s"Invalid division $value"

}
