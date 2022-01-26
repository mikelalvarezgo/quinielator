package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api

import java.rmi.UnexpectedException
import scala.concurrent.{ExecutionContext, Future}

import akka.actor.ActorSystem
import akka.http.javadsl.model.StatusCodes
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import cats.data.OptionT
import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.domain.OddsClient
import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.marshaller.FootballApiMarshaller._
import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.request.FetchLeagueRequest
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.api.LeagueDayResponse
import com.mikelalvarezgo.quinielator.shared.domain.contract.Logger
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.auto._
import io.circe.syntax.EncoderOps

final class FootballApiClient(config: FootballApiConfig)(
  implicit ec: ExecutionContext,
  system: ActorSystem,
  logger: Logger
) extends OddsClient[Future] {
  private val headers = Seq(
    RawHeader("x-rapidapi-host", config.baseUri),
    RawHeader("x-rapidapi-key", config.apiKey)
  )
  implicit val marshallerConfig: Configuration =
    Configuration.default.withSnakeCaseMemberNames
  override def fetchLeagueDay(
    round: Int,
    year: Int
  ): OptionT[Future, LeagueDayResponse] = {
    val params = FetchLeagueRequest(
      s"Regular Season - $round",
      config.firstDivisionId.toString,
      year.toString
    )
    val entity = HttpEntity(MediaTypes.`application/json`, params.asJson.spaces2)
    val request = HttpRequest(uri = Uri(s"${config.baseUri}/fixtures"))
      .withEntity(entity)
      .withHeaders(headers)

    OptionT(
      Http()
        .singleRequest(request)
        .flatMap { response =>
          response.status match {
            case StatusCodes.OK =>
              Unmarshal(response.entity.withContentType(ContentTypes.`application/json`))
                .to[LeagueDayResponse]
                .map(_.some)
            case _ =>
              logger.warning(
                s"Error in fetching leagueDay from api football, status: ${response.status}"
              )
              throw new UnexpectedException(
                s"Unexpected exception when retrieving league day, status: ${response.status}"
              )
          }
        }
    )
  }
}
