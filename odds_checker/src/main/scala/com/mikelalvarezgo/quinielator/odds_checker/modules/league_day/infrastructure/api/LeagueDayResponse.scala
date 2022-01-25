package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api

import org.joda.time.DateTime

final case class LeagueDayResponse(games: Seq[GameResponse]) {
  val beginDate: DateTime = new DateTime(games.map(_.playedAt).min)
  val endDate: DateTime   = new DateTime(games.map(_.playedAt).max)
}
