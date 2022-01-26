package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api

import com.typesafe.config.Config

final case class FootballApiConfig(
  baseUri: String,
  apiKey: String,
  firstDivisionId: Int,
  secondDivisionId: Int
)

object FootballApiConfig {
  def fromConfig(config: Config) =
    FootballApiConfig(
      config.getString("base-uri"),
      config.getString("api-key"),
      config.getInt("first-division-id"),
      config.getInt("second-division-id")
    )
}
