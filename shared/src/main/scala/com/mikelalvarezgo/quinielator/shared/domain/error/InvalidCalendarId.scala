package com.mikelalvarezgo.quinielator.shared.domain.error

case class InvalidCalendarId(value: String) extends ValidationError {
  override def message: String = s"Invalid calendarId $value"
}
