package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import org.mongodb.scala.MongoClient

final class MongoConnection(config: MongoConfig) {

  private val client: MongoClient = MongoClient(config.url)

  val db = client.getDatabase("quinielator")
}
