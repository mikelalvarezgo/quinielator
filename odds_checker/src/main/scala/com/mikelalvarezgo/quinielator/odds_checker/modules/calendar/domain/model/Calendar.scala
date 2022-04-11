package com.mikelalvarezgo.quinielator.odds_checker.modules.calendar.domain.model

import com.mikelalvarezgo.quinielator.shared.domain.model.{CalendarId, LeagueDayId, Season}

final case class Calendar(id: CalendarId, season: Season, leagueDays: Seq[LeagueDayId])
