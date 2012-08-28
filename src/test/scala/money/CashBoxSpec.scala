package money

import org.specs2.mutable.Specification

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/28
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */
class CashBoxSpec extends Specification {
  "金庫" should {
    "入金できる" in {
      val cashBox = new CashBox
      cashBox.put('hoge, Money._100) must equalTo(Money._100)
    }
    "残高が取得できる" in {
      val cashBox = new CashBox
      cashBox.put('hoge, Money._100)
      cashBox.put('hoge, Money._100)
      cashBox.put('hoge, Money._100)
      cashBox.put('hoge, Money._100)
      cashBox.balance('hoge) must equalTo(Money(400))
    }
    "出金できる" in {
      "残高が足りている場合は指定された金額が出金される" in {
        val cashBox = new CashBox
        cashBox.put('hoge, Money(10000))
        cashBox.get('hoge, Money(1050)) must equalTo(Money(1050))
      }
      "残高が不足している場合は出金できない" in {
        val cashBox = new CashBox
        cashBox.put('hoge, Money._10)
        cashBox.get('hoge, Money._100) must equalTo(Money._0)
      }
    }
  }
}
