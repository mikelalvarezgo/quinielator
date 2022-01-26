package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.acceptance

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerAcceptanceTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub.GetLeagueDayCommandStub

final class GetLeagueDayUseCaseSpec extends OddsCheckerAcceptanceTestCase {

  "GetLeagueDayUseCaseSpec" should {
    "fetch football api and store the data in mongodb" in runWithContext{ context =>
      val command = GetLeagueDayCommandStub.create()
      val result = context.leagueDayContext.getLeagueDayUseCase.execute(command)
      result.isValid shouldBe true
      result.getResult shouldBe ()
    }
  }
}
