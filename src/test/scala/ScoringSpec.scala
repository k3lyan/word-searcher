import model.Scoring
import org.scalatest.flatspec.AnyFlatSpec

class ScoringSpec extends AnyFlatSpec {
  it should "give a score of 100 if the text file contains all the words from the query" in {
    val score: Float = Scoring(List("a", "query"), List("We", "are", "looking", "for", "a", "specific", "query")).loopSearch
    assert(score == 100)
    }

  it should "give a score of 0 if the text file does not contain any word of the query" in {
    val score: Float = Scoring(List("a", "query"), List("We", "are", "looking", "for", "the", "wrong", "thing")).loopSearch
    assert(score == 0)
  }

  it should "equal to the ratio of the number of words from the query present in the text and the total number of words in the query" in {
    val score: Float = Scoring(List("This", "is", "a", "query"), List("Here", "is", "a", "file")).loopSearch
    assert(score == 50)
  }
}
