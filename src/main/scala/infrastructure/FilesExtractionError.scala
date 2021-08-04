package infrastructure

sealed trait FilesExtractionError extends Exception with Product {
  val message: String
}

object FilesExtractionError {
  final case class InvalidFileExtraction(dir: String) extends FilesExtractionError {
    override val message: String = s"Could not get the list of text files for directory $dir "
  }
}