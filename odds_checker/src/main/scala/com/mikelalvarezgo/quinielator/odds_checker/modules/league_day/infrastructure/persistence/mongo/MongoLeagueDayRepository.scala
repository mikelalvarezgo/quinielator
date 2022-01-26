package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.persistence.mongo

import scala.concurrent.{ExecutionContext, Future}

import cats.data.OptionT
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.contract.LeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoConnection
import com.mikelalvarezgo.quinielator.shared.infrastructure.utils.SyntaxUtils._

final class MongoLeagueDayRepository(mongoConnection: MongoConnection)(
  implicit ec: ExecutionContext
) extends LeagueDayRepository[Future]
    with MongoLeagueDayMapping {
  val collectionName = "league_day"
  val collection     = mongoConnection.getCollection("league_day")

  override def create(leagueDay: LeagueDay): Future[Unit] =
    collection.insertOne(leagueDay).toFuture().map(_ => ())

  override def update(leagueDay: LeagueDay): Future[Unit] =
    collection
      .findOneAndReplace(leagueDay.leagueDayId.toFilter, leagueDay)
      .toFuture()
      .map(_ => ())

  override def delete(id: LeagueDayId): Future[Unit] =
    collection.deleteOne(id.toFilter).toFuture().map(_ => ())

  override def find(id: LeagueDayId): OptionT[Future, LeagueDay] =
    OptionT(collection.find(id.toFilter).toFuture().map(_.headOption)).transform2
}
