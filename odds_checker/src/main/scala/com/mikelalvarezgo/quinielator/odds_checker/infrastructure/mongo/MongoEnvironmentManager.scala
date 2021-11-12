package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.mongo

import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoCollectionHelper
import org.mongodb.scala.MongoClient

object MongoEnvironmentManager {
  val mongoClient: MongoClient =
    MongoClient("mongodb://127.0.0.1:27017")
  val mongoCollectionHelpers: Seq[MongoCollectionHelper] =
    Seq(new LeagueDayCollectionHelper(mongoClient))
}
