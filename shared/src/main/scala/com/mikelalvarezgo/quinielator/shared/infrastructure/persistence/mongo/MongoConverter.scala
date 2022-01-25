package com.mikelalvarezgo.quinielator.shared.infrastructure.persistence.mongo

import org.mongodb.scala.bson.Document

trait MongoConverter[T] {
  def toValue(document: Document): T
}
