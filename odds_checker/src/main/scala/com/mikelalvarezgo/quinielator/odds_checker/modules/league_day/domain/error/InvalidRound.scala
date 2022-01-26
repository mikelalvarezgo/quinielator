package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.error

import com.mikelalvarezgo.quinielator.shared.domain.error.ValidationError

case class InvalidRound(value: Int) extends ValidationError
