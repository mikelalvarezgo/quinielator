package com.mikelalvarezgo.quinielator.odds_checker.infrastructure

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import cats.implicits.catsStdInstancesForFuture
import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.LeagueDayGetter
import com.mikelalvarezgo.quinielator.shared.infrastructure.BehaviourTestCase

class OddsCheckerBehaviourTestCase extends BehaviourTestCase{


  val leagueDayRepository = mock[LeagueDayRepository[Future]]
  val oddsClient = mock[OddsClient[Future]]

  //Services
  val leagueDayGetter = new LeagueDayGetter[Future](leagueDayRepository, oddsClient)
  //Use-cases
  val getDayLeagueUseCase = new GetLeagueDayUseCase(leagueDayGetter)
}
