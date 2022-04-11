package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error

import com.mikelalvarezgo.quinielator.shared.domain.error.DomainError
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId

final case class LeagueDayNotFound(value: LeagueDayId) extends DomainError {
  override def message: String = s"LeagueDay ${value.raw} not found"
}
