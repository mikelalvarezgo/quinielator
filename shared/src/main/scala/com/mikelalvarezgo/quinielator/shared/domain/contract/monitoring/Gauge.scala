package com.mikelalvarezgo.quinielator.shared.domain.contract.monitoring

trait Gauge {
  def increment(): Unit
  def decrement(): Unit
  def add(num: Int): Unit
  def subtract(num: Int): Unit
}
