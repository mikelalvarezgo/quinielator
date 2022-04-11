package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.acceptance

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerAcceptanceTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayQuery
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub.{GetLeagueDayQueryStub, LeagueDayStub}
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.LeagueDayIdStub

final class GetLeagueDayUseCaseSpec extends OddsCheckerAcceptanceTestCase {

  "GetLeagueDayUseCaseSpec" should {
    "get the data in mongodb" in runWithContext { context =>
      import context.ec
      import context.leagueDayContext
      val leagueDayId: LeagueDayId = LeagueDayIdStub.create()
      val query: GetLeagueDayQuery = GetLeagueDayQueryStub.create(leagueDayId.raw)
      val leagueDay: LeagueDay     = LeagueDayStub.create(leagueDayId)

      val result =
        leagueDayContext.repository
          .create(leagueDay)
          .map(_ => leagueDayContext.getLeagueDayUseCase.execute(query))

      result.futureValue.isValid shouldBe true
      result.futureValue.getResult shouldBe leagueDay
    }
  }
}
