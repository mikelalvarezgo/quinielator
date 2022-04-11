package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.persistence.mongo

import scala.concurrent.{ExecutionContext, Future}

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.contract.CalendarRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model.Calendar
import com.mikelalvarezgo.quinielator.shared.domain.model.CalendarId
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoConnection
import com.mikelalvarezgo.quinielator.shared.infrastructure.utils.SyntaxUtils._

final class MongoCalendarRepository(mongoConnection: MongoConnection)(
  implicit ec: ExecutionContext
) extends CalendarRepository[Future]
    with MongoCalendarMapping {
  val collectionName = "calendar"
  val collection     = mongoConnection.getCollection("calendar")

  override def create(calendar: Calendar): Future[Unit] =
    collection.insertOne(calendar).toFuture().map(_ => ())

  override def update(calendar: Calendar): Future[Unit] =
    collection
      .findOneAndReplace(calendar.id.toFilter, calendar)
      .toFuture()
      .map(_ => ())

  override def delete(id: CalendarId): Future[Unit] =
    collection.deleteOne(id.toFilter).toFuture().map(_ => ())

  override def find(id: CalendarId): OptionT[Future, Calendar] =
    OptionT(collection.find(id.toFilter).toFuture().map(_.headOption)).transform2
}
