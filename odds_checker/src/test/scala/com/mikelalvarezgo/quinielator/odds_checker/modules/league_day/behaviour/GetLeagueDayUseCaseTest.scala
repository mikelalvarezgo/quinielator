package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.behaviour

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import cats.data.{NonEmptyList, OptionT}
import cats.data.Validated.Invalid
import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerBehaviourTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayQuery
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error.LeagueDayNotFound
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub.{GetLeagueDayQueryStub, LeagueDayStub}
import com.mikelalvarezgo.quinielator.shared.domain.error.InvalidLeagueDayId
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.LeagueDayIdStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.utils.SyntaxUtils._

final class GetLeagueDayUseCaseTest extends OddsCheckerBehaviourTestCase {

  "Get case" should {
    "should get the league Day from repository" in {
      val leagueDayId: LeagueDayId = LeagueDayIdStub.create()
      val query: GetLeagueDayQuery = GetLeagueDayQueryStub.create(leagueDayId.raw)
      val leagueDay: LeagueDay     = LeagueDayStub.create(leagueDayId)

      when(leagueDayRepository.find(leagueDayId)).thenReturn(leagueDay.someT[Future])

      val result = getDayLeagueUseCase.execute(query)
      result.isValid shouldBe true
      result.toOption.get.futureValue shouldBe leagueDay
    }
    "should return Not Found error if there is no league day for id" in {
      val leagueDayId: LeagueDayId = LeagueDayIdStub.create()
      val query: GetLeagueDayQuery = GetLeagueDayQueryStub.create(leagueDayId.raw)

      when(leagueDayRepository.find(leagueDayId))
        .thenReturn(OptionT.none[Future, LeagueDay])

      val result = getDayLeagueUseCase.execute(query)
      result.isValid shouldBe true
      result.toOption.get.failed.futureValue shouldBe LeagueDayNotFound(leagueDayId)
    }
  }
  "should return validation error if id is not valid" in {
    val query: GetLeagueDayQuery = GetLeagueDayQueryStub.invalid()

    val result = getDayLeagueUseCase.execute(query)
    result.isValid shouldBe false
    result shouldBe Invalid(NonEmptyList.one(InvalidLeagueDayId(query.id)))
  }

}
