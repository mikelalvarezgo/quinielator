package com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types

import scala.util.Random

object IntStub {

  def random: Int = Random.nextInt()

  def between(min: Int, max: Int): Int = Random.between(min, max)

  def randomNaturalNumber: Int = Random.nextInt().abs
}
