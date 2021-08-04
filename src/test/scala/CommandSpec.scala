import infrastructure.Command.search
import infrastructure.FileSystem.FileData
import model.Result.Score
import org.scalatest.flatspec.AnyFlatSpec

class CommandSpec extends AnyFlatSpec{
  // In case someone change the best 10 results requirement, this test will fail
  it should "only render te 10 best results" in {
    val query = "This is a query with more words than other examples"
    val filesData = List(
      FileData("file1", List("This", "is", "a", "query", "with", "more", "words", "than", "other", "examples")),
      FileData("file2", List("This", "is", "a", "query", "with", "more", "words", "than", "other")),
      FileData("file3", List("This", "is", "a", "query", "with", "more", "words", "than")),
      FileData("file4", List("This", "is", "a", "query", "with", "more", "words")),
      FileData("file5", List("This", "is", "a", "query", "with", "more")),
      FileData("file6", List("This", "is", "a", "query", "with")),
      FileData("file7", List("This", "is", "a", "query")),
      FileData("file8", List("This", "is", "a")),
      FileData("file9", List("This", "is")),
      FileData("file10", List("This")),
      FileData("file11", List("This", "too")),
      FileData("file12", List("I", "do")),
      FileData("file13", List("I", "do"))
    )

    val scores = List(
      Score("file1", 100),
      Score("file2", 90),
      Score("file3", 80),
      Score("file4", 70),
      Score("file5", 60),
      Score("file6", 50),
      Score("file7", 40),
      Score("file8", 30),
      Score("file9", 20),
      Score("file10", 10)
    )
    assert(search(query, filesData) == scores)
  }
}
