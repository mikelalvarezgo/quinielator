package com.mikelalvarezgo.quinielator.shared.domain.model

import org.joda.time.DateTime

final case class LeagueDay(
  leagueDayId: LeagueDayId,
  beginDate: DateTime,
  endDate: DateTime,
  games: Seq[Game]
)
