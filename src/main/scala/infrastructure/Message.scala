package infrastructure

import infrastructure.FileSystem.FileData

sealed trait Message

object Message {
  final case class Search(query: String, filesData: List[FileData]) extends Message
  final case object Quit extends Message
}
