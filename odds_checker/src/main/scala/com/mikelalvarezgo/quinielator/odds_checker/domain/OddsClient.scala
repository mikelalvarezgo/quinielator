package com.mikelalvarezgo.quinielator.odds_checker.domain

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDayResponse

trait OddsClient[P[_]] {
  def fetchLeagueDay(round: Int, year: Int): OptionT[P, LeagueDayResponse]
}
