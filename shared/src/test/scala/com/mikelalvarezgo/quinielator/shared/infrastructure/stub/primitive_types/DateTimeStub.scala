package com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types

import scala.util.Random

import org.joda.time.DateTime

object DateTimeStub {

  def now: DateTime  = DateTime.now()

  def randomPast: DateTime = new DateTime(System.nanoTime() - Random.nextInt().abs)

  def randomFuture: DateTime =  new DateTime(System.nanoTime() + Random.nextInt().abs)

  def random: DateTime = if(Random.nextBoolean()) randomFuture else randomPast
}
