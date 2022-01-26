package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.{GameResponse, LeagueDayResponse}
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.ListStub

object LeagueDayResponseStub {

  def create(
            games: List[GameResponse] = ListStub.gen()(GameResponseStub.create())
            ): LeagueDayResponse = LeagueDayResponse(games)
}
