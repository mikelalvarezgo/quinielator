package com.mikelalvarezgo.quinielator.shared.domain.model

import org.joda.time.DateTime

final case class Game(homeTeam: String,
                      visitorTeam: String,
                      goalsHomeTeam: Int,
                      goalsVisitorTeam: Int,
                      date: DateTime,
                      division: Division
                     ){
  def isHomeVictory = goalsHomeTeam > goalsVisitorTeam
  def isVisitorVictory = goalsVisitorTeam > goalsHomeTeam
  def isTied = goalsVisitorTeam == goalsHomeTeam
  def isForecastCorrect(forecasts:Seq[Forecast]) = forecasts.exists(forecast =>
    forecast match {
      case HomeWin => isHomeVictory
      case VisitorWin => isVisitorVictory
      case Tie => isTied
    }
  )
}