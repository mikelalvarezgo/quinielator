package com.mikelalvarezgo.quinielator.shared.infrastructure.stub

import com.mikelalvarezgo.quinielator.shared.domain.model.{Division, Game}
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.{DateTimeStub, IntStub, StringStub}
import org.joda.time.DateTime

object GameStub {

  def create(
    homeTeam: String = StringStub.random(10),
    visitorTeam: String = StringStub.random(10),
    homeTeamGoals: Int = IntStub.randomNaturalNumber,
    visitorTeamGoals: Int = IntStub.randomNaturalNumber,
    playedAt: DateTime = DateTimeStub.random,
    division: Division = DivisionStub.random
  ): Game =
    Game(homeTeam, visitorTeam, homeTeamGoals, visitorTeamGoals, playedAt, division)
}
