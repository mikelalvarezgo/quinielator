package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.persistence

import cats.implicits._
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.persistence.mongo.MongoCalendarRepository
import com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub.{CalendarIdStub, CalendarStub}
import com.mikelalvarezgo.quinielator.shared.infrastructure.IntegrationTestCase

final class MongoCalendarRepositoryTest extends IntegrationTestCase {
  val repository = new MongoCalendarRepository(mongoConnection)

  "MongoCalendarRepository" should {
    "store calendar in database" in {
      val calendarId = CalendarIdStub.create()
      val calendar   = CalendarStub.create(calendarId)
      repository.create(calendar).futureValue shouldBe ()
      eventually(
        repository.find(calendarId).value.futureValue shouldBe calendar.some
      )
    }

    "delete calendar in database" in {
      val calendarId = CalendarIdStub.create()
      val calendar   = CalendarStub.create(calendarId)
      repository.create(calendar).futureValue shouldBe ()
      repository.delete(calendarId)
      eventually(
        repository.find(calendarId).value.futureValue shouldBe None
      )
    }
  }
}
