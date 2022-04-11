package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.infrastructure.stub

import com.mikelalvarezgo.quinielator.shared.domain.model.Season
import com.mikelalvarezgo.quinielator.shared.infrastructure.stub.primitive_types.IntStub

object SeasonStub {

  def random(value: String = generateRandom): Season = Season.unsafe(value)

  def invalid(value: String = "2022/2022"): Season = Season(value)

  private def generateRandom = {
    val year1 = IntStub.between(1920, 2050)
    s"${year1}/${year1 + 1}"
  }
}
