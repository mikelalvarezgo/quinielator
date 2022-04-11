package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application.CreateCalendarUseCase.CreateCalendarCommand
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.ListStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.LeagueDayIdStub

object CreateCalendarCommandStub {

  def create(
    id: String = CalendarIdStub.create().raw,
    season: String = SeasonStub.random().value,
    leagueDays: Seq[String] = ListStub.gen[String]()(LeagueDayIdStub.create().raw)
  ): CreateCalendarCommand =
    CreateCalendarCommand(id, season, leagueDays)

}
