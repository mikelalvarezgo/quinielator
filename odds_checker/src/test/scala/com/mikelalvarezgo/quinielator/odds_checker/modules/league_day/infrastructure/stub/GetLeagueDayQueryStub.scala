package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayQuery
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.LeagueDayIdStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.StringStub

object GetLeagueDayQueryStub {

  def create(id: String = LeagueDayIdStub.create().raw): GetLeagueDayQuery =
    GetLeagueDayQuery(id)

  def invalid(id: String = StringStub.random(10)): GetLeagueDayQuery =
    GetLeagueDayQuery(id)

}
