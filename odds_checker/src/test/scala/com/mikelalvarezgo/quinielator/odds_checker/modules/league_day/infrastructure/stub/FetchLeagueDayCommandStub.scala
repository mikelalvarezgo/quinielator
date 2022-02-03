package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.FetchLeagueDayUseCase.FetchLeagueDayCommand
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.IntStub

object FetchLeagueDayCommandStub {

  def create(
            year: Int = IntStub.between(2017, 2021),
            round: Int = IntStub.between(1, 38)
            ): FetchLeagueDayCommand = FetchLeagueDayCommand(round, year)

  def invalidRound(
              year: Int = IntStub.between(2017, 2021),
              round: Int = IntStub.between(50, 80)
            ): FetchLeagueDayCommand = FetchLeagueDayCommand(round, year)
}
