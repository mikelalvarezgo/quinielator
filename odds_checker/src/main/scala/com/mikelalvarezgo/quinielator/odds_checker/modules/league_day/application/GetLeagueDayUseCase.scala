package com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application

import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.application.GetLeagueDayUseCase.GetLeagueDayQuery
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.model.LeagueDay
import com.mikelalvarezgo.quinielator.odds_checker.modules.league_day.domain.service.LeagueDayGetter
import com.mikelalvarezgo.quinielator.shared.domain.{Query, UseCase}
import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation
import com.mikelalvarezgo.quinielator.shared.domain.model.LeagueDayId

final class GetLeagueDayUseCase[P[_]](getter: LeagueDayGetter[P])
    extends UseCase[P, GetLeagueDayQuery] {
  override def execute(
    query: GetLeagueDayQuery
  ): Validation[P[GetLeagueDayQuery#Response]] =
    validate(query).map(getter.execute)

  private def validate(query: GetLeagueDayQuery) =
    LeagueDayId.fromString(query.id)
}

object GetLeagueDayUseCase {
  case class GetLeagueDayQuery(id: String) extends Query {
    override type Response = LeagueDay
  }
}
