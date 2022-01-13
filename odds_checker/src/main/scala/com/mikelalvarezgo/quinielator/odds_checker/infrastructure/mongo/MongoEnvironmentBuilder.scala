package com.mikelalvarezgo.quinielator.odds_checker.infrastructure.mongo

object MongoEnvironmentBuilder extends App {
  MongoEnvironmentManager.mongoCollectionHelpers.foreach(_.createCollection())
}
