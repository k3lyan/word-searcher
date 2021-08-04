import infrastructure.FileSystem.{FileData, directoryExists, getFileContent, getfilesList}
import infrastructure.FilesExtractionError.InvalidFileExtraction
import infrastructure.Message.{Quit, Search}
import presentation.Console.{CONSOLE_TOKEN, printToken}
import presentation.State
import presentation.State.INITIAL_STATE

import scala.util.Try

object SearchApp {

  def printInit(directory: String, listFiles: List[String]) = {
    println(s"${listFiles.length} files has been found in directory ${directory}")
    printToken(CONSOLE_TOKEN)
  }

  def init(argList: List[String]): Either[Exception, List[FileData]] = Try {
    val files: List[String] = getfilesList(argList.head)
    if (files != List("")) printInit(argList.head, files)
    Right(files.map(filename => FileData(filename, getFileContent(filename))))
  }.getOrElse {
    if (argList.length == 0) Left(new IllegalArgumentException("No directory given to index."))
    else if (!directoryExists(argList.head)) Left(new IllegalArgumentException("The directory given to index does not exist"))
    else Left(InvalidFileExtraction(argList.head))
  }

  def run(filesData: List[FileData]): State =
    io.Source.stdin.getLines()
      .foldLeft(INITIAL_STATE)((state: State, newLine: String) => {
        newLine match {
          case ":q" => state.compute(Quit)
          case "" => state.emptyState
          case _: String => state.compute(Search(newLine, filesData))
        }
      })
}
