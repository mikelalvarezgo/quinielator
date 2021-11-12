package com.mikelalvarezgo.quinielator.shared.domain.model

final case class Bet(game: Game, forecast: Seq[Forecast]) {
  def isAHit: Boolean = game.isForecastCorrect(forecast)
}
