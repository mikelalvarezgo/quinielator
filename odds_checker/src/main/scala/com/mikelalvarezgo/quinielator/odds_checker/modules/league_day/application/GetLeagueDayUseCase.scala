package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application

import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayCommand
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.Round
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.LeagueDayGetter
import com.mikelalvarezgo.quinielator.shared.domain.{Command, UseCase}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation

final class GetLeagueDayUseCase[P[_]](getter: LeagueDayGetter[P]) extends UseCase[P, GetLeagueDayCommand] {
  override def execute(command: GetLeagueDayCommand): Validation[P[GetLeagueDayCommand#Response]] =
    validate(command).mapN(getter.execute)

  private def validate(command: GetLeagueDayCommand) =
    (
      Round.fromInt(command.round),
      command.year.valid
    )
}

object GetLeagueDayUseCase {

  case class GetLeagueDayCommand(round: Int, year: Int) extends Command

}
