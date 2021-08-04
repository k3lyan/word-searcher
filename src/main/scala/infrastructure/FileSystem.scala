package infrastructure

import scala.sys.process.{Process, stringSeqToProcess}

object FileSystem {
  def directoryExists(directory: String): Boolean = Seq("test", "-d", directory).! == 0
  def getFileContent(filename: String): List[String] = io.Source.fromFile(filename).getLines.flatMap(_.split("\\W+")).toList
  def getfilesList(directory: String): List[String] = Process(Seq("find", directory, "-maxdepth", "1", "-type", "f", "-name", "*.txt"))
    .!!
    .split ("\\n")
    .map (_.trim)
    .toList
  final case class FileData(title: String, content: List[String])
}
