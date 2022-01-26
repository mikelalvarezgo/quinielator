package com.mikelalvarezgo.quinielator.shared.infrastructure.stub

import java.util.UUID

import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId

object LeagueDayIdStub {

  def create(id: UUID = UUID.randomUUID()): LeagueDayId = LeagueDayId(id)
}
