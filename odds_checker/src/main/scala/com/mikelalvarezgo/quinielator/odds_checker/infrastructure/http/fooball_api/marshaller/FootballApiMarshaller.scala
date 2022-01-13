package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.marshaller

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.{GameResponse, LeagueDayResponse}
import io.circe.{ACursor, Decoder}

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
      goalsHomeTeam <- hCursor.downField("goals.home").as[Int]
      goalsAwayTeam <- hCursor.downField("goals.away").as[Int]
      division      <- hCursor.downField("league.name").as[String]
    } yield GameResponse(homeTeam, visitorTeam, goalsHomeTeam, goalsAwayTeam, division)
  )
}
