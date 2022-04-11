package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error.LeagueDayNotFound
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId
import com.mikelalvarezgo.quinielator.shared.utils.ThrowableTypeClasses.MError

final class LeagueDayGetter[P[_]: MError](leagueDayRepository: LeagueDayRepository[P]) {

  def execute(leagueDayId: LeagueDayId): P[LeagueDay] =
    leagueDayRepository
      .find(leagueDayId)
      .getOrElseF(MError[P].raiseError[LeagueDay](LeagueDayNotFound(leagueDayId)))
}
