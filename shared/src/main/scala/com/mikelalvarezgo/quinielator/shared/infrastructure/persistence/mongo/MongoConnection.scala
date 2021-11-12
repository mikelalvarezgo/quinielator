package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import com.mongodb.{MongoClientSettings, ServerAddress}
import org.mongodb.scala.MongoClient

import scala.jdk.CollectionConverters.SeqHasAsJava

final class MongoConnection(mongoConfig: MongoConfig) {

  val settings: MongoClientSettings = MongoClientSettings.builder()
    .applyToClusterSettings(b => b.hosts(List(new ServerAddress(mongoConfig.host)).asJava))
    .build()
  private val client: MongoClient = MongoClient(settings)

  val db = client.getDatabase("quinielator")
}
