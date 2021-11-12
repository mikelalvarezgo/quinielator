package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import com.typesafe.config.Config

final case class MongoConfig (host: String,
                              port: Int)

object MongoConfig{
  def fromConfig(config: Config) =
    MongoConfig(
      config.getString("host"),
      config.getInt("port")
    )
}
