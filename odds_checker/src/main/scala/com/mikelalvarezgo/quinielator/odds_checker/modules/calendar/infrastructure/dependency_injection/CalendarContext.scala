package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.dependency_injection

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.application.CreateCalendarUseCase
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.contract.CalendarRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.persistence.mongo.MongoCalendarRepository
import com.mikelalvarezgo.quinielator.shared.infrastructure.dependency_injection.Context
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoConnection

final class CalendarContext(
  mongoConnection: MongoConnection
)(implicit ec: ExecutionContext)
    extends Context {
  val repository: CalendarRepository[Future] = new MongoCalendarRepository(
    mongoConnection
  )

  //Use cases
  val createCalendarUseCase: CreateCalendarUseCase[Future] =
    new CreateCalendarUseCase[Future](repository)

  //Dispatcher

  override def dispatcher = new CalendarDispatcher(createCalendarUseCase)
}
