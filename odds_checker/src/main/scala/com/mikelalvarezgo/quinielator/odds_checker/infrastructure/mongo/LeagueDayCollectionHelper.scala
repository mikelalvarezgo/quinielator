package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.mongo

import com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo.MongoCollectionHelper
import org.mongodb.scala.MongoClient

final class LeagueDayCollectionHelper(mongoClient: MongoClient) extends MongoCollectionHelper {
  override val collectionName: String       = "leagueDay"
  implicit override val client: MongoClient = mongoClient
}
