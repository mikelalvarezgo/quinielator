package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api

final case class GameResponse(
  homeTeam: String,
  visitorTeam: String,
  playedAt: Long,
  goalsHomeTeam: Int,
  goalsVisitorTeam: Int,
  division: String
)
