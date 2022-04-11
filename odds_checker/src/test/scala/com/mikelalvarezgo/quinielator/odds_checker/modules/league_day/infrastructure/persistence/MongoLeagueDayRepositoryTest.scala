package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.persistence

import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.persistence.mongo.MongoLeagueDayRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.infrastructure.stub.LeagueDayStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.IntegrationTestCase
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.LeagueDayIdStub
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.DateTimeStub

final class MongoLeagueDayRepositoryTest extends IntegrationTestCase {
  val repository = new MongoLeagueDayRepository(mongoConnection)

  "MongoLeagueDayRepository" should {
    "store league day in database" in {
      val leagueDayId = LeagueDayIdStub.create()
      val leagueDay   = LeagueDayStub.create(leagueDayId)
      repository.create(leagueDay).futureValue shouldBe ()
      eventually(
        repository.find(leagueDayId).value.futureValue shouldBe leagueDay.some
      )
    }
    "update league day in database" in {
      val leagueDayId = LeagueDayIdStub.create()
      val leagueDay   = LeagueDayStub.create(leagueDayId)
      repository.create(leagueDay).futureValue shouldBe ()
      val updatedLeagueDay = leagueDay.withEndDate(DateTimeStub.randomFuture)
      repository.update(updatedLeagueDay)
      eventually(
        repository.find(leagueDayId).value.futureValue shouldBe updatedLeagueDay.some
      )
    }

    "delete league day in database" in {
      val leagueDayId = LeagueDayIdStub.create()
      val leagueDay   = LeagueDayStub.create(leagueDayId)
      repository.create(leagueDay).futureValue shouldBe ()
      repository.delete(leagueDayId)
      eventually(
        repository.find(leagueDayId).value.futureValue shouldBe None
      )
    }
  }
}
