package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import com.typesafe.config.Config

final case class MongoConfig(host: String, port: Int) {
  val url = s"mongo//:$host:$port"
}

object MongoConfig {
  def fromConfig(config: Config) =
    MongoConfig(
      config.getString("host"),
      config.getInt("port")
    )
}
