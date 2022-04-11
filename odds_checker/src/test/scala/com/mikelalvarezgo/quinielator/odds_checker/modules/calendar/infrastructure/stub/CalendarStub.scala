package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model.Calendar
import com.mikelalvarezgo.quinielator.shared.domain.model.{CalendarId, LeagueDayId, Season}
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.ListStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.LeagueDayIdStub

object CalendarStub {

  def create(
    id: CalendarId = CalendarIdStub.create(),
    season: Season = SeasonStub.random(),
    leagueDays: List[LeagueDayId] = ListStub.gen[LeagueDayId]()(LeagueDayIdStub.create())
  ): Calendar =
    Calendar(id, season, leagueDays)

}
