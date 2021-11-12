package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.mongo

object MongoEnvironmentDropper extends App {
  MongoEnvironmentManager.mongoCollectionHelpers.foreach(_.deleteCollection())
}
