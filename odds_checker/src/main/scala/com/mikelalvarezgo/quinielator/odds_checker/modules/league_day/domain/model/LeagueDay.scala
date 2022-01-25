package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model

import org.joda.time.DateTime

import com.mikelalvarezgo.quinielator.shared.domain.model.{Game, LeagueDayId}

final case class LeagueDay(leagueDayId: LeagueDayId, beginDate: DateTime, endDate: DateTime, games: Seq[Game])
