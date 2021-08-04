import SearchApp.{init, run}

object Main {
  def main(args: Array[String]) {
    init(args.toList) match {
      case Right(contents) => run(contents)
      case Left(e) => throw e
    }
  }
}
