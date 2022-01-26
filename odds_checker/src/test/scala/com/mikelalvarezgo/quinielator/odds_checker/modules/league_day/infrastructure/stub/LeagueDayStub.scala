package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub

import org.joda.time.DateTime

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.shared.domain.model.{Game, LeagueDayId}
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.{DateTimeStub, ListStub}
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.{GameStub, LeagueDayIdStub}

object LeagueDayStub {

  def create(
            id: LeagueDayId = LeagueDayIdStub.create(),
            beginDate: DateTime = DateTimeStub.randomPast,
            endDate: DateTime = DateTimeStub.randomFuture,
            games: Seq[Game] = ListStub.gen()(GameStub.create())
            ): LeagueDay = LeagueDay(id, beginDate, endDate, games)
}
