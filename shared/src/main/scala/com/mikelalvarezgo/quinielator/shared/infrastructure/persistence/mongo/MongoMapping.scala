package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import com.mikelalvarezgo.quinielator.shared.domain.model.Id
import org.joda.time.DateTime
import org.mongodb.scala.bson.{BsonInt32, _}

trait MongoMapping {

  implicit class IdConverter(value: Id) {
    def toBsonObjectId: BsonObjectId = BsonObjectId(value.raw)
  }

  implicit class StringConverter(value: String) {
    def toBsonString: BsonString = BsonString(value)
  }

  implicit class IntConverter(value: Int) {
    def toBsonInt: BsonInt32 = BsonInt32(value)
  }

  implicit class DateConverter(value: DateTime) {
    def toBsonDateTime: BsonDateTime = BsonDateTime(value.getMillis)
  }

  implicit class bsonDateConverter(value: BsonDateTime) {
    def toDateTime: DateTime = new DateTime(value.getValue)
  }

}
