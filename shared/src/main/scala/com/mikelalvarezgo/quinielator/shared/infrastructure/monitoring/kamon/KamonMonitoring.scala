package com.mikelalvarezgo.quinielator.shared.infrastructure.monitoring.kamon

import com.mikelalvarezgo.quinielator.shared.domain.contract.monitoring.{Counter, Gauge, Histogram, Monitoring}


object KamonMonitoring extends Monitoring {
  override def counter(name: String, tags: Map[String, String] = Map.empty): Counter = new KamonCounter(name, tags)
  override def histogram(name: String, tags: Map[String, String] = Map.empty): Histogram =
    new KamonHistogram(name, tags)
  override def gauge(name: String, tags: Map[String, String] = Map.empty): Gauge = new KamonGauge(name, tags)
}
