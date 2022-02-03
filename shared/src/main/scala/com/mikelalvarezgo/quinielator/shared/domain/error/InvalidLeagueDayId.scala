package com.mikelalvarezgo.quinielator.shared.domain.error

case class InvalidLeagueDayId(value: String) extends ValidationError {
  override def message: String = s"Invalid leagueDayId $value"
}
