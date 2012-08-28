package money

import org.specs2.mutable._

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/19
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
class MoneySpec extends Specification {
  "Money" should {
    "equal to other same amount Money" in {
      Money(1) must equalTo(Money._1)
      Money(100) must equalTo(Money._100)
    }
    "加算できる" in {
      Money(1) + Money(2) must equalTo(Money(3))
    }
    "減算できる" in {
      Money(2) - Money(1) must equalTo(Money(1))
    }
    "比較" in {
      "大小比較" in {
        (Money(2) > Money(1)) must equalTo(true)
        (Money(2) > Money(3)) must equalTo(false)
      }
    }
  }
}
