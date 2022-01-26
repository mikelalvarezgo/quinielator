package com.mikelalvarezgo.quinielator.odds_checker.domain

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.LeagueDayResponse

trait OddsClient[P[_]] {
  def fetchLeagueDay(round: Int, year: Int): OptionT[P, LeagueDayResponse]
}
