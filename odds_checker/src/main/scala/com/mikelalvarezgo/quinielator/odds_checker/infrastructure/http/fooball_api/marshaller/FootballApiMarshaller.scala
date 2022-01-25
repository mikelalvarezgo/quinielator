package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.marshaller

import io.circe.{ACursor, Decoder}

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.{GameResponse, LeagueDayResponse}

object FootballApiMarshaller {
  private val responsePath: ACursor => ACursor = _.downField("response")
  implicit val leagueDayResponse: Decoder[LeagueDayResponse] = Decoder.instance(hCursor =>
    for {
      matches <- responsePath(hCursor).downField("response").as[Seq[GameResponse]]
    } yield LeagueDayResponse(matches)
  )

  implicit val gameMarshaller: Decoder[GameResponse] = Decoder.instance(hCursor =>
    for {
      homeTeam      <- hCursor.downField("teams.home.name").as[String]
      visitorTeam   <- hCursor.downField("teams.away.name").as[String]
      date          <- hCursor.downField("fixture.date").as[Long]
      goalsHomeTeam <- hCursor.downField("goals.home").as[Int]
      goalsAwayTeam <- hCursor.downField("goals.away").as[Int]
      division      <- hCursor.downField("league.name").as[String]
    } yield GameResponse(homeTeam, visitorTeam, date, goalsHomeTeam, goalsAwayTeam, division)
  )
}
