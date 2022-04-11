package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub

import java.util.UUID

import com.mikelalvarezgo.quinielator.shared.domain.model.CalendarId

object CalendarIdStub {

  def create(id: UUID = UUID.randomUUID()): CalendarId = CalendarId(id)

}
