package com.mikelalvarezgo.quinielator.odds_checker.infrastructure

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import cats.implicits.catsStdInstancesForFuture
import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application.CreateCalendarUseCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.contract.CalendarRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.{FetchLeagueDayUseCase, GetLeagueDayUseCase}
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.{LeagueDayFetcher, LeagueDayGetter}
import com.mikelalvarezgo.quinielator.shared.infrastructure.BehaviourTestCase

class OddsCheckerBehaviourTestCase extends BehaviourTestCase {

  val leagueDayRepository = mock[LeagueDayRepository[Future]]
  val calendarRepository  = mock[CalendarRepository[Future]]
  val oddsClient          = mock[OddsClient[Future]]

  //Services
  val leagueDayFetcher = new LeagueDayFetcher[Future](leagueDayRepository, oddsClient)
  val leagueDayGetter  = new LeagueDayGetter[Future](leagueDayRepository)
  //Use-cases
  val fetchDayLeagueUseCase = new FetchLeagueDayUseCase(leagueDayFetcher)
  val getDayLeagueUseCase   = new GetLeagueDayUseCase(leagueDayGetter)
  val createCalendarUseCase = new CreateCalendarUseCase(calendarRepository)
}
