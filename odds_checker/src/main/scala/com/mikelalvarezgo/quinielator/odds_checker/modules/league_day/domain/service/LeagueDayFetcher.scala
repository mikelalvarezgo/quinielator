package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service

import cats.Monad

import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.Round
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.ResponseConverter.LeagueDayResponseConverter
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId
import com.mikelalvarezgo.quinielator.shared.infrastructure.utils.SyntaxUtils._

final class LeagueDayFetcher[P[_]: Monad](
  repository: LeagueDayRepository[P],
  client: OddsClient[P]
) {
  def execute(round: Round, year: Int): P[Unit] =
    client
      .fetchLeagueDay(round.value, year)
      .flatFold(())(response => repository.create(response.toModel(LeagueDayId.create)))

}
