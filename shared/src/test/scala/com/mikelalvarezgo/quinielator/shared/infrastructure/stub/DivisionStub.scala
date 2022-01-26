package com.mikelalvarezgo.quinielator.shared.infrastructure.stub

import scala.util.Random

import com.mikelalvarezgo.quinielator.shared.domain.model.Division

object DivisionStub {

  def random: Division = Random.shuffle(Division.all).head
}
