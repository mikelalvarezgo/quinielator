package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error.validation

import com.mikelalvarezgo.quinielator.shared.domain.error.ValidationError

case class InvalidRound(value: Int) extends ValidationError {
  override def message: String = s"Invalid value $value for round"
}
