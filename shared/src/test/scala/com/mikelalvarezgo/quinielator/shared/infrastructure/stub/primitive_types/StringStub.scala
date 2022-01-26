package com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types

import scala.util.Random

object StringStub {

  def random(numChars: Int): String = Random.alphanumeric.take(numChars).toString()
}
