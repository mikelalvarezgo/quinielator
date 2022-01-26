package com.mikelalvarezgo.quinielator.shared.infrastructure

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt

import akka.actor.ActorSystem
import org.scalatest.{EitherValues, OptionValues}
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import com.mikelalvarezgo.quinielator.shared.domain.contract.Logger
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.{MongoConfig, MongoConnection}
import com.typesafe.config.ConfigFactory

abstract class IntegrationTestCase
  extends AnyWordSpecLike
    with Matchers
    with OptionValues
    with ScalaFutures
    with Eventually
    with EitherValues {
  implicit override val patienceConfig: PatienceConfig = PatienceConfig(timeout = 60.seconds, interval = 500.millis)
  val config = ConfigFactory.defaultApplication()

  val mongoConnection = new MongoConnection(MongoConfig("localhost", 27017, "mikel", "test"))
  implicit val system: ActorSystem          = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  implicit val logger: Logger               = DummyLogger
}
