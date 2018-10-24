package com.book.app

import scalaz.NonEmptyList

trait Drone[F[_]] {
  def getBacklog: F[Int]

  def getAgents: F[Int]
}

final case class MachineNode(id: String)

trait Machines[F[_]] {
  def getTime: Epoch

  def getManaged: F[NonEmptyList[MachineNode]]
}