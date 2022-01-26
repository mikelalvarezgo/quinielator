package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId

trait LeagueDayRepository[P[_]] {
  def create(leagueDay: LeagueDay): P[Unit]
  def update(leagueDay: LeagueDay): P[Unit]
  def delete(id: LeagueDayId): P[Unit]
  def find(id: LeagueDayId): OptionT[P, LeagueDay]
}
