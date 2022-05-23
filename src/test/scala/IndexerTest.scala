import com.SearchApp.indexer
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec
class IndexerTest extends AnyFlatSpec with Matchers{
  it should "have size greater than 0" in {
    val path = getClass.getResource("/TextDir")
    val index = indexer(path.getPath)
    index.size should be > 0
  }
}
