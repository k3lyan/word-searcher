package model

sealed trait Result

object Result {
  final case object Empty extends Result
  final case class Score(filename: String, result: Int) extends Result
}
