package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.request

final case class FetchLeagueRequest(
  round: Int,
  leagueId: Int,
  season: Int
)
