package com.mikelalvarezgo.quinielator.shared.domain.model

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime

final case class Quiniela(
  leagueDay: LeagueDay,
  bets: Seq[Bet],
  plenoAlQuince: PlenoAlQuince,
  beginDate: DateTime,
  endDate: DateTime
) {
  def checkHits: Int  = bets.count(_.isAHit)
  def isASuccess: Int = bets.count(_.isAHit)
}
