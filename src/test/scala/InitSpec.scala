import org.scalatest.flatspec.AnyFlatSpec


class InitSpec extends AnyFlatSpec {

  it should "produce IllegalArgumentException when argList is empty" in {
    assertThrows[IllegalArgumentException] {
      throw SearchApp
        .init(List())
        .left
        .get
    }
  }

  it should "produce IllegalArgumentException when the directory does not exist" in {
    assertThrows[IllegalArgumentException] {
      throw SearchApp
        .init(List("foo/bar/"))
        .left
        .get
    }
  }
}
