package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueQuery
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.LeagueDayGetter
import com.mikelalvarezgo.quinielator.shared.domain.{Query, UseCase}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId

final class GetLeagueDayUseCase[P[_]](getter: LeagueDayGetter[P])
    extends UseCase[P, GetLeagueQuery] {
  override def execute(
    query: GetLeagueQuery
  ): Validation[P[GetLeagueQuery#Response]] =
    validate(query).map(getter.execute)

  private def validate(query: GetLeagueQuery) =
    LeagueDayId.fromString(query.id)
}

object GetLeagueDayUseCase {
  case class GetLeagueQuery(id: String) extends Query {
    override type Response = LeagueDay
  }
}
