package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.dependency_injection

import scala.concurrent.Future

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.FetchLeagueDayUseCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.FetchLeagueDayUseCase.FetchLeagueDayCommand
import com.mikelalvarezgo.quinielator.shared.domain.Request
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.ValidationFutureT
import com.mikelalvarezgo.quinielator.shared.infrastructure.dependency_injection.Dispatcher

final class LeagueDayDispatcher(
  getLeagueDayUseCase: FetchLeagueDayUseCase[Future]
) extends Dispatcher {

  override def dispatch: Request ==> ValidationFutureT[Unit] = {
    case req: FetchLeagueDayCommand => getLeagueDayUseCase.execute(req)
  }

}
