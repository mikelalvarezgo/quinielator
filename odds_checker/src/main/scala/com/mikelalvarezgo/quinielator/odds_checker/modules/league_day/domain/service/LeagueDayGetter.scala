package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service

import cats.Functor
import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.Round

final class LeagueDayGetter[P[_]: Functor](repository: LeagueDayRepository[P], client: OddsClient[P]) {
  def execute(round: Round,
              year: Int
             ) = client.fetchLeagueDay(round.value, year).foldF()

}
