package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.http

import com.mikelalvarezgo.quinielator.odds_checker.infrastructure.http.fooball_api.{FootballApiClient, FootballApiConfig}
import com.mikelalvarezgo.quinielator.shared.infrastructure.IntegrationTestCase

final class FootballApiClientTest extends IntegrationTestCase {

  val client = new FootballApiClient(
    FootballApiConfig(
      baseUri = "https://api-football-v1.p.rapidapi.com/v3",
      apiKey = "JriokpUwEsmshKxYBIlsaQwcQ4s7p1atR2ujsnslOvJWSE5Nf2",
      firstDivisionId = 140,
      secondDivisionId = 141
    )
  )
  "FootballApi client " should {
    "fetch correct results for past leagueDay" in {
      val response = client.fetchLeagueDay(1, 2018)
      response.value.futureValue.isDefined shouldBe true
    }
  }
}
