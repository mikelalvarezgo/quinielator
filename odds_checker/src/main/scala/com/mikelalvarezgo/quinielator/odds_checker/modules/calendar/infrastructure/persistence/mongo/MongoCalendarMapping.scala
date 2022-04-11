package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.persistence.mongo

import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model.Calendar
import com.mikelalvarezgo.quinielator.shared.domain.model.{CalendarId, LeagueDayId, Season}
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoMapping
import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoUtils._
import org.mongodb.scala.bson.Document
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters

trait MongoCalendarMapping extends MongoMapping {

  implicit class calendarIdConverter(calendarId: CalendarId) {
    def toFilter: Bson = Filters.eq("_id", calendarId.toBsonString)
  }

  implicit def calendarToDocument(calendar: Calendar): Document =
    Document(
      "_id"      -> calendar.id.toBsonString,
      "season"   -> calendar.season.value.toBsonString,
      "gameDays" -> calendar.leagueDays.map(_.raw)
    )

  implicit def documentToLeagueDay(document: Document): Calendar =
    Calendar(
      CalendarId.unsafe(document.getStr("_id")),
      Season.unsafe(document.getStr("season")),
      document.getListString("gameDays").map(LeagueDayId.unsafe)
    )
}
