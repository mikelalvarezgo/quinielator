package com.mikelalvarezgo.quinielator.shared.domain.error

case class InvalidSeason(value: String) extends ValidationError {
  override def message: String = s"Invalid season $value"
}
