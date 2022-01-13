package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model

final case class LeagueDayResponse(games: Seq[GameResponse])

final case class GameResponse(
  homeTeam: String,
  visitorTeam: String,
  goalsHomeTeam: Int,
  goalsVisitorTeam: Int,
  division: String
)
