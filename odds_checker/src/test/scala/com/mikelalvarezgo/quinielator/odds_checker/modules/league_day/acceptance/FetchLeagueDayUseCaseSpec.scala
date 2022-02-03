package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.acceptance

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerAcceptanceTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub.FetchLeagueDayCommandStub

final class FetchLeagueDayUseCaseSpec extends OddsCheckerAcceptanceTestCase {

  "FetchLeagueDayUseCaseSpec" should {
    "fetch football api and store the data in mongodb" in runWithContext{ context =>
      val command = FetchLeagueDayCommandStub.create()
      val result = context.leagueDayContext.fetchLeagueDayUseCase.execute(command)
      result.isValid shouldBe true
      result.getResult shouldBe ()
    }
  }
}
