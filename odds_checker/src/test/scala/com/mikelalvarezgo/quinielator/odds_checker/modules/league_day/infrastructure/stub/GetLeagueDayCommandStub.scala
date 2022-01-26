package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayCommand
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.IntStub

object GetLeagueDayCommandStub {

  def create(
            year: Int = IntStub.between(2017, 2021),
            round: Int = IntStub.between(1, 38)
            ): GetLeagueDayCommand = GetLeagueDayCommand(round, year)

  def invalidRound(
              year: Int = IntStub.between(2017, 2021),
              round: Int = IntStub.between(50, 80)
            ): GetLeagueDayCommand = GetLeagueDayCommand(round, year)
}
