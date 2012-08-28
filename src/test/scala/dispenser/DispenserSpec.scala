package dispenser

import org.specs2.mutable.{Before, Specification}
import money.Money
import product.Drink

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/19
 * Time: 1:47
 * To change this template use File | Settings | File Templates.
 */
class DispenserSpec extends Specification {

  "Dispenser" should {
    "お金を投入できる" in {
      val dispenser = new Dispenser
      dispenser.dropIn(Money._100) must equalTo(None)
    }
    "投入済み金額を取得できる" in {
      val dispenser = new Dispenser
      dispenser.dropIn(Money._100)
      dispenser.dropIn(Money._500)
      dispenser.dropedInTotal must equalTo(Money(600))
    }
    "払い戻しができる" in {
      val dispenser = new Dispenser
      dispenser.dropIn(Money._100)
      dispenser.dropIn(Money._500)
      dispenser.dropedInTotal must equalTo(Money(600))
      dispenser.refund must equalTo(Money(600))
      dispenser.dropedInTotal must equalTo(Money._0)
    }
    "投入できないお金は返却される" in {
      val dispenser = new Dispenser
      dispenser.dropIn(Money._1) must equalTo(Some(Money._1))
      dispenser.dropedInTotal must equalTo(Money._0)
    }
    "在庫を補充できる" in {
      val dispenser = new Dispenser
      dispenser.fillIn(Drink.cola) must equalTo(1)
      dispenser.fillIn(Drink.cola) must equalTo(2)
      dispenser.fillIn(Drink.cola) must equalTo(3)
    }
    "飲み物の在庫が取得できる" in {
      val dispenser = new Dispenser
      dispenser.fillIn(Drink.cola)
      dispenser.fillIn(Drink.cola)
      dispenser.fillIn(Drink.cola)
      dispenser.stockCount(Drink.cola) must equalTo(3)
    }
    "飲み物の在庫一覧が取得できる" in {
      val dispenser = new Dispenser
      dispenser.fillIn(Drink.cola)
      dispenser.fillIn(Drink.cola)
      dispenser.fillIn(Drink.dew)
      dispenser.fillIn(Drink.dr_pepper)
      dispenser.fillIn(Drink.red_bull)
      dispenser.fillIn(Drink.red_bull)
      dispenser.stocks must equalTo(Map(Drink.cola -> 2, Drink.dew -> 1, Drink.dr_pepper -> 1, Drink.red_bull -> 2))
    }

    "購入可否が確認できる" in {
      "在庫が足りない場合は購入できない" in {
        val dispenser = new Dispenser
        dispenser.dropIn(Money._500)
        dispenser.buyable(Drink.cola) must equalTo(false)
      }
      "投入金額が不足している場合は購入できない" in {
        val dispenser = new Dispenser
        dispenser.dropIn(Money._10)
        dispenser.fillIn(Drink.cola)
        dispenser.fillIn(Drink.cola)
        dispenser.buyable(Drink.cola) must equalTo(false)
      }
    }

    "購入可能な商品一覧を取得できる" in {
      val dispenser = new Dispenser
      (1 to 5).foreach{_=>dispenser.fillIn(Drink.cola)}
      (1 to 5).foreach{_=>dispenser.fillIn(Drink.dew)}
      (1 to 5).foreach{_=>dispenser.fillIn(Drink.red_bull)}
      dispenser.dropIn(Money._100)
      dispenser.dropIn(Money._50)
      val products = dispenser.buyableProducts
      products must haveLength(2)
      products must contain(Drink.cola)
      products must contain(Drink.dew)
    }

    "売上金を取得できる" in {
      val dispenser = new Dispenser
      dispenser.dropIn(Money._100)
      dispenser.dropIn(Money._100)
      dispenser.fillIn(Drink.cola)
      dispenser.buy(Drink.cola)

      dispenser.sales must equalTo(Money(120))
    }

    "購入" in {
      "購入可能な場合は商品とお釣りを受け取れる" in {
        val dispenser = new Dispenser
        dispenser.dropIn(Money._100)
        dispenser.dropIn(Money._100)
        dispenser.fillIn(Drink.cola)
        dispenser.buy(Drink.cola) must equalTo(Some(Drink.cola))
      }
      "購入不可な場合は何も出てこない" in {
        val dispenser = new Dispenser
        dispenser.dropIn(Money._100)
        dispenser.fillIn(Drink.cola)
        dispenser.buy(Drink.cola) must equalTo(None)
      }
      "購入すると売上が増え　投入金額が減る" in {
        val dispenser = new Dispenser
        dispenser.dropIn(Money._100)
        dispenser.dropIn(Money._100)
        dispenser.fillIn(Drink.cola)

        dispenser.buy(Drink.cola)

        dispenser.sales must equalTo(Money(120))
        dispenser.dropedInTotal must equalTo(Money(80))
      }
    }
  }
}
