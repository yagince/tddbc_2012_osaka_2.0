package dispenser

import org.specs2.mutable.Specification
import product.Product
import money.Money

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/28
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
class StockSpec extends Specification {
  val sampleProduct1 = Product("hoge", Money(100))
  val sampleProduct2 = Product("foo", Money(120))

  "在庫" should {
    "補充できる" in {
      val stock = new Stock
      stock.fillIn(sampleProduct1) must equalTo(1)
    }
    "在庫数を取得できる" in {
      val stock = new Stock
      (1 to 4).foreach{_ => stock.fillIn(sampleProduct1)}
      stock.stockCount(sampleProduct1) must equalTo(4)
    }
    "出庫" in {
      "在庫がある場合は出庫できる" in {
        val stock = new Stock
        (1 to 4).foreach{_ => stock.fillIn(sampleProduct1)}
        stock.delivery(sampleProduct1) must equalTo(Some(sampleProduct1))
      }
      "在庫がない場合は出庫できない" in {
        val stock = new Stock
        stock.delivery(sampleProduct1) must equalTo(None)
      }
    }
    "在庫一覧が取得できる" in {
      val stock = new Stock
      (1 to 5).foreach{_=>stock.fillIn(sampleProduct1)}
      (1 to 10).foreach{_=>stock.fillIn(sampleProduct2)}
      stock.stocks must equalTo(Map(sampleProduct1 -> 5, sampleProduct2 -> 10))
    }
  }
}
