package com.mikelalvarezgo.quinielator.shared.domain.error

final case class InvalidForecast(value: String) extends ValidationError {
  override def message: String = s"Invalid forecast $value"

}
