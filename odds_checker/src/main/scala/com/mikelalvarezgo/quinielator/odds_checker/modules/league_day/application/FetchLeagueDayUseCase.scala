package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application

import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.FetchLeagueDayUseCase.FetchLeagueDayCommand
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.Round
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.LeagueDayFetcher
import com.mikelalvarezgo.quinielator.shared.domain.{Command, UseCase}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation

final class FetchLeagueDayUseCase[P[_]](fetcher: LeagueDayFetcher[P])
    extends UseCase[P, FetchLeagueDayCommand] {
  override def execute(
    command: FetchLeagueDayCommand
  ): Validation[P[FetchLeagueDayCommand#Response]] =
    validate(command).mapN(fetcher.execute)

  private def validate(command: FetchLeagueDayCommand) =
    (
      Round.fromInt(command.round),
      command.year.valid
    )
}

object FetchLeagueDayUseCase {

  case class FetchLeagueDayCommand(round: Int, year: Int) extends Command

}
