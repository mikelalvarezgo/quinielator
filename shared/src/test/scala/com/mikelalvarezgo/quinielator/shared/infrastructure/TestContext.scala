package com.mikelalvarezgo.quinielator.shared.infrastructure

import com.mikelalvarezgo.quinielator.shared.infrastructure.dependency_injection.Context

trait TestContext extends Context {
  def closeResources(): Unit
}
