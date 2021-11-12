package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import org.mongodb.scala.MongoClient

abstract class MongoCollectionHelper {

  implicit val client: MongoClient

  val collectionName: String

  def createCollection(): Unit = {
    client.getDatabase("quinielator").createCollection(collectionName)
    ()
  }

  def deleteCollection(): Unit = {
    client.getDatabase("quinielator").getCollection(collectionName).drop()
    ()
  }
}
