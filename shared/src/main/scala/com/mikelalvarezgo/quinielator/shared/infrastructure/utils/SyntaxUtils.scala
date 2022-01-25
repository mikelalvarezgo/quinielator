package com.mikelalvarezgo.quinielator.shared.infrastructure.utils

import cats.Monad
import cats.data.OptionT
import cats.implicits._

object SyntaxUtils {

  implicit class OptionTOps[P[_]: Monad, A](value: OptionT[P, A]) {
    def flatFold[B](default: => B)(f: A => P[B]): P[B] = value.foldF(default.pure[P])(f)
  }

  implicit class OptionTTransformSyntax[P[_]: Monad, A, B](value: OptionT[P, A]) {
    def transform2(implicit convert: A => B): OptionT[P, B] = value.map(convert)
  }
}
