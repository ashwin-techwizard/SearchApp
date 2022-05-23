import com.SearchApp.{indexer, search}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SearchTest extends AnyFlatSpec with Matchers{
  it should "have size greater than 0" in {
    val path = getClass.getResource("/TextDir")
    val index = indexer(path.getPath)
    val hits = search("sigma", index)
    hits.head._2 shouldEqual 100
  }
}
