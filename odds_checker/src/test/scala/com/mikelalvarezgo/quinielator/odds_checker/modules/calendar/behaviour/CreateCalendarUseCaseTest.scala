package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.behaviour

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import cats.data.NonEmptyList
import cats.data.Validated.Invalid
import cats.implicits._

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.OddsCheckerBehaviourTestCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model.Calendar
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub.{CreateCalendarCommandStub, SeasonStub}
import com.mikelalvarezgo.quinielator.shared.domain.error.InvalidSeason

final class CreateCalendarUseCaseTest extends OddsCheckerBehaviourTestCase {

  "CreateCalendarUseCase" should {
    "return validation error if season is not valid" in {
      val command = CreateCalendarCommandStub.create(season = SeasonStub.invalid().value)
      val result  = createCalendarUseCase.execute(command)

      result.isValid shouldBe false
      result shouldBe Invalid(NonEmptyList.one(InvalidSeason(command.season)))
    }

    "store calendar in repository if it is valid" in {
      val command = CreateCalendarCommandStub.create()

      when(calendarRepository.create(any[Calendar])).thenReturn(().pure[Future])

      val result = createCalendarUseCase.execute(command)
      result.isValid shouldBe true
      result.toOption.get.futureValue shouldBe ()
    }
  }

}
