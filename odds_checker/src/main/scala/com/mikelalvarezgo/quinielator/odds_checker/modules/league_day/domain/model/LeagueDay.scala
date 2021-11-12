package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model

import com.mikelalvarezgo.quinielator.shared.domain.model.{Game, LeagueDayId}

import org.joda.time.DateTime

final case class LeagueDay(leagueDayId: LeagueDayId,
                           beginDate: DateTime,
                           endDate: DateTime,
                           games: Seq[Game])
