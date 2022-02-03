package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.behaviour

import cats.implicits._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import cats.data.NonEmptyList
import cats.data.Validated.Invalid
import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerBehaviourTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.FetchLeagueDayUseCase.FetchLeagueDayCommand
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error.validation.InvalidRound
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.LeagueDayResponse
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub.{FetchLeagueDayCommandStub, LeagueDayResponseStub}
import com.mikelalvarezgo.quinielator.shared.infrastructure.utils.SyntaxUtils._

final class FetchLeagueDayUseCaseTest extends OddsCheckerBehaviourTestCase {

  "GetLeagueDayUse case" should{
    "should get the info from client and store in repository" in {
      val command: FetchLeagueDayCommand = FetchLeagueDayCommandStub.create()
      val response: LeagueDayResponse = LeagueDayResponseStub.create()

      when(oddsClient.fetchLeagueDay(command.round, command.year)).thenReturn(response.someT[Future])
      when(leagueDayRepository.create(any[LeagueDay])).thenReturn(().pure[Future])

      val result =  fetchDayLeagueUseCase.execute(command)
      result.isValid shouldBe true
      result.toOption.get.futureValue shouldBe ()
    }
    "should return validation error if division is not valid" in {
      val command: FetchLeagueDayCommand = FetchLeagueDayCommandStub.invalidRound()

      val result =  fetchDayLeagueUseCase.execute(command)
      result.isValid shouldBe false
      result shouldBe Invalid(NonEmptyList.one(InvalidRound(command.round)))
    }
  }

}
