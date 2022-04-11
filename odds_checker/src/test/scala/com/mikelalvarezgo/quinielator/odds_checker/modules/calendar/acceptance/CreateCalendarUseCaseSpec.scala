package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.acceptance

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerAcceptanceTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub.CreateCalendarCommandStub

final class CreateCalendarUseCaseSpec extends OddsCheckerAcceptanceTestCase {

  "CreateCalendarUseCaseSpec" should {
    "fetch football api and store the data in mongodb" in runWithContext { context =>
      val command = CreateCalendarCommandStub.create()
      val result  = context.calendarContext.createCalendarUseCase.execute(command)
      result.isValid shouldBe true
      result.getResult shouldBe ()
    }
  }
}
