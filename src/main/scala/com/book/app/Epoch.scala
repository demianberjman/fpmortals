package com.book.app

import scala.concurrent.duration.FiniteDuration

final case class Epoch(millis: Long) extends AnyVal {

  def +(other: FiniteDuration): Epoch = {
    Epoch(other.toMillis + millis)
  }

  def -(other: FiniteDuration): Epoch = {
    Epoch(other.toMillis - millis)
  }
}
