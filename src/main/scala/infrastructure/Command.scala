package infrastructure

import infrastructure.FileSystem.FileData
import model.Result.Score
import model.Scoring

object Command {
    def search(query: String, filesData: List[FileData]): List[Score] =
      filesData
        .map(file => Score(file.title, Scoring(query.split(" ").toList, file.content).loopSearch.toInt))
        .sortBy(_.result)(Ordering.Int.reverse)
        .take(10)
  }

