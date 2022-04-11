package com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types

import scala.util.Random

object ListStub {

  def gen[T](
    numberOfElements: Int = Random.nextInt(100) + 1
  )(elementCreator: => T): List[T] =
    (1 to numberOfElements).map(_ => elementCreator).toList
}
