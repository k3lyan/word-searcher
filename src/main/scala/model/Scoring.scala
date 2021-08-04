package model

final case class Scoring(sentence: List[String], text: List[String]) {
  val loopSearch: Float = (sentence.filter(text.contains).length.toFloat / sentence.length) * 100
}

object Scoring {
  def apply(query: List[String] = List(""),
            contentFile: List[String] = List("")) = new Scoring(query, contentFile)
}
