package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.dependency_injection

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.depedency_injection.OddsCheckerContext
import com.mikelalvarezgo.quinielator.shared.domain.contract.Logger
import com.mikelalvarezgo.quinielator.shared.infrastructure.{DummyLogger, TestContext}
import com.typesafe.config.Config

final class OddsCheckerContextTest(testConfig: Config) extends OddsCheckerContext with TestContext{

  override lazy val config: Config = testConfig
  override val logger: Logger = DummyLogger

  override def closeResources(): Unit = ()

}
