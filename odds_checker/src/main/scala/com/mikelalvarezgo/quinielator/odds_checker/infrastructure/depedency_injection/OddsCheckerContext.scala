package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.depedency_injection

import akka.actor.ActorSystem
import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.{FootballApiClient, FootballApiConfig}
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.dependency_injection.LeagueDayContext
import com.mikelalvarezgo.quinielator.shared.domain.contract.Logger
import com.mikelalvarezgo.quinielator.shared.infrastructure.dependency_injection.Context
import com.mikelalvarezgo.quinielator.shared.infrastructure.logger.ScalaLoggingLogger
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.{MongoConfig, MongoConnection}
import com.typesafe.config.ConfigFactory

class OddsCheckerContext extends Context {

  implicit val logger: Logger = ScalaLoggingLogger("odds-checker-logger")
  lazy val config             = ConfigFactory.defaultApplication.getConfig("odds-checker")
  implicit val system         = ActorSystem.create("odds-checker")
  implicit val ec             = system.dispatcher

  val mongoConnection = new MongoConnection(
    MongoConfig.fromConfig(config.getConfig("persistence.mongo"))
  )
  val oddsClient = new FootballApiClient(
    FootballApiConfig.fromConfig(config.getConfig("client.football-api"))
  )

  //contexts
  val leagueDayContext = new LeagueDayContext(oddsClient, mongoConnection)

  override def dispatcher = leagueDayContext.dispatcher

}
