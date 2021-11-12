package com.mikelalvarezgo.quinielator.shared.domain

import com.mikelalvarezgo.quinielator.shared.domain.error.Validation.Validation

trait Request {
  type Response
}

trait UseCase[T[_], R <: Request] {
  def execute(r: R): Validation[T[R#Response]]
}
