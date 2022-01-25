package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.shared.domain.model.{Division, Game, LeagueDayId}
import org.joda.time.DateTime

object ResponseConverter {

  implicit class LeagueDayResponseConverter(response: LeagueDayResponse) {
    def toModel(id: LeagueDayId) =
      LeagueDay(
        leagueDayId = id,
        beginDate = response.beginDate,
        endDate = response.endDate,
        games = response.games.map(_.toModel)
      )
  }

  implicit class GameResponseConverter(response: GameResponse) {
    def toModel: Game =
      Game(
        response.homeTeam,
        response.visitorTeam,
        response.goalsHomeTeam,
        response.goalsHomeTeam,
        new DateTime(response.playedAt),
        Division.unsafe(response.division)
      )
  }
}
