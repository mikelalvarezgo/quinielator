package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.dependency_injection

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.{FetchLeagueDayUseCase, GetLeagueDayUseCase}
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.{LeagueDayFetcher, LeagueDayGetter}
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.persistence.mongo.MongoLeagueDayRepository
import com.mikelalvarezgo.quinielator.shared.infrastructure.dependency_injection.Context
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoConnection

final class LeagueDayContext(
  oddsClient: OddsClient[Future],
  mongoConnection: MongoConnection
)(implicit ec: ExecutionContext)
    extends Context {

  val repository: LeagueDayRepository[Future] = new MongoLeagueDayRepository(
    mongoConnection
  )
  // services
  val leagueDayFetcher: LeagueDayFetcher[Future] =
    new LeagueDayFetcher[Future](repository, oddsClient)
  val leagueDayGetter: LeagueDayGetter[Future] = new LeagueDayGetter[Future](repository)
  //use cases
  val fetchLeagueDayUseCase = new FetchLeagueDayUseCase(leagueDayFetcher)
  val getLeagueDayUseCase   = new GetLeagueDayUseCase(leagueDayGetter)

  override def dispatcher = new LeagueDayDispatcher(fetchLeagueDayUseCase)
}
