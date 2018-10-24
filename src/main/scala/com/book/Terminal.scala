package com.book

import scala.concurrent.Future

trait Terminal[C[_]] {
  def read: C[String]

  def write(t: String): C[Unit]
}

trait Execution[C[_]] {

  def chain[A, B](c: C[A])(f: A => C[B]): C[B]

  def create[B](b: B): C[B]
}


object TerminalSync extends Terminal[Now] {
  override def read: String = ???

  override def write(t: String): Unit = ???
}

object TerminalAsync extends Terminal[Future] {
  def read: Future[String] = ???

  def write(t: String): Future[Unit] = ???
}

object Execution {

  implicit class Ops[A, C[_]](c: C[A]) {
    def flatMap[B](f: A => C[B])(implicit e: Execution[C]): C[B] = e.chain(c)(f)

    def map[B](f: A => B)(implicit e: Execution[C]): C[B] = e.chain(c)(f andThen e.create)
  }

  def echo[C[_]](implicit t: Terminal[C], e: Execution[C]): C[String] =

    for {
      in <- t.read
      _ <- t.write(in)
    } yield in
}
