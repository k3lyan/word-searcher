package presentation

import model.Result
import model.Result.{Empty, Score}

object Console {
  val CONSOLE_TOKEN: String = "search> "
  def printToken(token: String): Unit = print(token)

  def printResult(results: List[Result]): Unit = {
    results.foreach{
      case Score(filename, result) => println(s"${filename.split("/").toList.reverse.head}: ${result}%")
      case Empty => println("No score.")
    }
    printToken(s"${CONSOLE_TOKEN}")
  }
}
