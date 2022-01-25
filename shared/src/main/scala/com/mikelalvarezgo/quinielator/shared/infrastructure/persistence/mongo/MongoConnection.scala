package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import org.mongodb.scala.bson.Document
import org.mongodb.scala.{MongoClient, MongoCollection}

final class MongoConnection(config: MongoConfig) {

  private val client: MongoClient = MongoClient(config.url)

  val db = client.getDatabase("quinielator")

  def getCollection(collection: String): MongoCollection[Document] = db.getCollection(collection)
}
