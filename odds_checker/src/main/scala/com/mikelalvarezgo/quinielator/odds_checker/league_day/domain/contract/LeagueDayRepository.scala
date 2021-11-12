package com.mikelalvarezgo.quinielator.odds_checker.league_day.domain.contract

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.shared.domain.model.{LeagueDay, LeagueDayId}

trait LeagueDayRepository[P[_]] {
  def create(leagueDay: LeagueDay): P[Unit]
  def update(leagueDay: LeagueDay): P[Unit]
  def delete(id: LeagueDayId): P[Unit]
  def find(id: LeagueDayId): OptionT[P, LeagueDay]
}
