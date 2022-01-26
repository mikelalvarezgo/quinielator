package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import com.typesafe.config.Config
import org.mongodb.scala.{ConnectionString, MongoClientSettings, MongoCredential}

final case class MongoConfig(host: String, port: Int, user: String, password: String) {
  val url = s"mongodb://$host:$port/quinielator"
  val credential: MongoCredential =
    MongoCredential.createCredential(user, "quinielator", password.toCharArray)
  val settings: MongoClientSettings =
    MongoClientSettings
      .builder()
      .applyConnectionString(ConnectionString(url))
      .credential(credential)
      .build()
}

object MongoConfig {
  def fromConfig(config: Config) =
    MongoConfig(
      config.getString("host"),
      config.getInt("port"),
      config.getString("user"),
      config.getString("password")
    )
}
