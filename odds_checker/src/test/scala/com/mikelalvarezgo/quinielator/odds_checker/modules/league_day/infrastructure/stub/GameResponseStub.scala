package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.GameResponse
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.DivisionStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.{DateTimeStub, IntStub, StringStub}

object GameResponseStub {

  def create(
              homeTeam: String = StringStub.random(10),
              visitorTeam: String = StringStub.random(10),
              homeTeamGoals: Int = IntStub.randomNaturalNumber,
              visitorTeamGoals: Int = IntStub.randomNaturalNumber,
              playedAt: Long = DateTimeStub.random.getMillis,
              division: String = DivisionStub.random.value
            ): GameResponse =
    GameResponse(homeTeam, visitorTeam, playedAt, homeTeamGoals, visitorTeamGoals, division)
}
