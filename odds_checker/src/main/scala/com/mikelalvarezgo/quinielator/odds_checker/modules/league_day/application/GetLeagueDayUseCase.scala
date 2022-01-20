package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application

import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.FootballApiClient
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayCommand
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.{Request, UseCase}

final class GetLeagueDayUseCase[P[_]](client: OddsClient[P], repository: LeagueDayRepository[P]) extends UseCase[P, GetLeagueDayCommand]{
  override def execute(command: GetLeagueDayCommand): Validation[P[GetLeagueDayCommand#Response]] =
    validate(command).mapN(client.fetchLeagueDay)

  private def validate(command: GetLeagueDayCommand) = (
    (
      command.year.valid,
      command.round.valid
    )
  )
}

object GetLeagueDayUseCase {

  case class GetLeagueDayCommand(round: Int, year: Int) extends Request

}
