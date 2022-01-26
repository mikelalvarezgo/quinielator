package com.mikelalvarezgo.quinielator.odds_checker

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.depedency_injection.OddsCheckerContext

object OddsCheckerStarter extends App {

  val oddsCheckerContext = new OddsCheckerContext()

  oddsCheckerContext.logger.info("OddsChecker Started!")
}
