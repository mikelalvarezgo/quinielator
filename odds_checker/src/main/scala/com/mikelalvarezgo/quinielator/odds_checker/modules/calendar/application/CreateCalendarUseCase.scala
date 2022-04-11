package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application

import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application.CreateCalendarUseCase.CreateCalendarCommand
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.contract.CalendarRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model.Calendar
import com.mikelalvarezgo.quinielator.shared.domain.{Command, UseCase}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.model.{CalendarId, LeagueDayId, Season}

final case class CreateCalendarUseCase[P[_]](repository: CalendarRepository[P])
    extends UseCase[P, CreateCalendarCommand] {

  override def execute(r: CreateCalendarCommand): Validation[P[Unit]] =
    validate(r).mapN(Calendar.apply).map(repository.create)

  private def validate(command: CreateCalendarCommand) = (
    CalendarId.fromString(command.id),
    Season.fromString(command.season),
    command.leagueDaysId.map(LeagueDayId.fromString).sequence
  )

}

object CreateCalendarUseCase {
  final case class CreateCalendarCommand(
    id: String,
    season: String,
    leagueDaysId: Seq[String]
  ) extends Command

}
