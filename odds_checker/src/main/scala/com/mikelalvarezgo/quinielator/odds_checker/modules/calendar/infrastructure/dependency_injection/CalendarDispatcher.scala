package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.dependency_injection

import scala.concurrent.Future

import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application.CreateCalendarUseCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application.CreateCalendarUseCase.CreateCalendarCommand
import com.mikelalvarezgo.quinielator.shared.domain.Request
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.ValidationFutureT
import com.mikelalvarezgo.quinielator.shared.infrastructure.dependency_injection.Dispatcher

final class CalendarDispatcher(
  createCalendarUseCase: CreateCalendarUseCase[Future]
) extends Dispatcher {

  override def dispatch: Request ==> ValidationFutureT[Unit] = {
    case req: CreateCalendarCommand => createCalendarUseCase.execute(req)
  }

}
