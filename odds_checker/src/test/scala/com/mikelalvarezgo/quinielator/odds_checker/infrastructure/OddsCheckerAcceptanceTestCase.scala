package com.mikelalvarezgo.quinielator.odds_checker.infrastructure

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.dependency_injection.OddsCheckerContextTest
import com.mikelalvarezgo.quinielator.shared.infrastructure.AcceptanceSpec
import com.typesafe.config.ConfigFactory

class OddsCheckerAcceptanceTestCase extends AcceptanceSpec[OddsCheckerContextTest]{
  println(ConfigFactory.load("test/odds-checker.conf"))
  println(ConfigFactory.load("test/odds-checker.conf").getConfig("odds-checker"))
  override def context(): OddsCheckerContextTest = new OddsCheckerContextTest(ConfigFactory.load("test/odds-checker.conf"))
}
