package presentation

import infrastructure.Command.search
import infrastructure.Message
import infrastructure.Message.{Quit, Search}
import model.Result
import model.Result.Empty
import presentation.Console.{CONSOLE_TOKEN, printResult, printToken}

import scala.sys.exit


final case class State(oldResults: List[(String, List[Result])], result: List[Result]) {
  def compute(message: Message): State = message match {
    case Search(query, filesData) =>
      oldResults.find(_._1 == query)
        .fold(State(oldResults, search(query, filesData)).update(query))(oldResult => {
          printResult(oldResult._2)
          State(oldResults, oldResult._2)
        })
    case Quit => exit(0)
  }

  def emptyState = {
    printToken(s"${CONSOLE_TOKEN}")
    State(oldResults, result)
  }

  def update(query: String): State = {
    printResult(result)
    State((oldResults :+ (query, result)).takeRight(4), result)
  }
}

object State {
  val INITIAL_STATE: State = new State(List(("", List(Empty))), List(Empty))
  def apply(results: List[(String, List[Result])] = INITIAL_STATE.oldResults,
            result: List[Result] = INITIAL_STATE.result) = new State(results, result)
}
