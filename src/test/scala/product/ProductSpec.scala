package product

import org.specs2.mutable.Specification
import money.Money

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/26
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
class ProductSpec extends Specification {
  "商品" should {
    "金額が足りているか確認できる" in {
      "不足している場合はfalse" in {
        val product = Product("hoge", Money._100)
        product.isEnough(Money(10)) must equalTo(false)
      }
      "足りている場合はtrue" in {
        val product = Product("hoge", Money._100)
        product.isEnough(Money._100) must equalTo(true)
      }
    }
    "お釣りを計算できる" in {
      "指定した金額が商品の金額よりも高い場合は差分が取得できる" in {
        val product = Product("hoge", Money._100)
        product.calcChange(Money(300)) must equalTo(Money(200))
      }
      "指定した金額が商品の金額よりも低い場合は０円" in {
        val product = Product("hoge", Money._100)
        product.calcChange(Money._50) must equalTo(Money._0)
      }
    }
  }
}
