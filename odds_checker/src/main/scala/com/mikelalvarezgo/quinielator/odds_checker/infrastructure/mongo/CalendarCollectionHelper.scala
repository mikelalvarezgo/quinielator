package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.mongo

import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoCollectionHelper
import org.mongodb.scala.MongoClient

final class CalendarCollectionHelper(mongoClient: MongoClient)
    extends MongoCollectionHelper {
  override val collectionName: String       = "calendar"
  implicit override val client: MongoClient = mongoClient
}
