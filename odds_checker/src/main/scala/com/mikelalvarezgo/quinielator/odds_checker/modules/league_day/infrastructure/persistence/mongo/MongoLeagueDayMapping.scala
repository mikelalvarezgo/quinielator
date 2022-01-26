package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.persistence.mongo

import org.mongodb.scala.bson._
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.shared.domain.model.{Division, Game, LeagueDayId}
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.{MongoConverter, MongoMapping}
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoUtils._
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters

trait MongoLeagueDayMapping extends MongoMapping {

  implicit class leagueDayIdConverter(leagueDayId: LeagueDayId) {
    def toFilter: Bson = Filters.eq("_id", leagueDayId.toBsonString)
  }

  implicit class gameConverter(value: Game) {
    def toDocument: Document = Document(
      "homeTeam"         -> value.homeTeam.toBsonString,
      "visitorTeam"      -> value.visitorTeam.toBsonString,
      "goalsHomeTeam"    -> value.goalsHomeTeam.toBsonInt,
      "goalsVisitorTeam" -> value.goalsVisitorTeam.toBsonInt,
      "playedAt"         -> value.playedAt.toBsonDateTime,
      "division"         -> value.division.value.toBsonString
    )
  }

  implicit def leagueDayToDocument(leagueDay: LeagueDay): Document =
    Document(
      "_id"       -> leagueDay.leagueDayId.toBsonString,
      "beginDate" -> leagueDay.beginDate.toBsonDateTime,
      "endDate"   -> leagueDay.endDate.toBsonDateTime,
      "games"     -> leagueDay.games.map(_.toDocument)
    )

  implicit val gameMongoConverter: MongoConverter[Game] = (mongoDocument: Document) =>
    Game(
      mongoDocument.getStr("homeTeam"),
      mongoDocument.getStr("visitorTeam"),
      mongoDocument.getInt("goalsHomeTeam"),
      mongoDocument.getInt("goalsVisitorTeam"),
      mongoDocument.getDate("playedAt"),
      Division.unsafe(mongoDocument.getStr("division"))
    )

  implicit def documentToLeagueDay(document: Document): LeagueDay =
    LeagueDay(
      LeagueDayId.unsafe(document.getStr("_id")),
      document.getDate("beginDate"),
      document.getDate("endDate"),
      document.getValues[Game]("games")
    )

}
