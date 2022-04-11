package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.contract

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model.Calendar
import com.mikelalvarezgo.quinielator.shared.domain.model.CalendarId

trait CalendarRepository[P[_]] {
  def create(calendar: Calendar): P[Unit]
  def update(calendar: Calendar): P[Unit]
  def delete(id: CalendarId): P[Unit]
  def find(id: CalendarId): OptionT[P, Calendar]
}
