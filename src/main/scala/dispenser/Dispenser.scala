package dispenser
import money.{CashBox, Money}
import product.Product

class Dispenser {

  private val cashBox = new CashBox
  private val stock = new Stock

  def dropIn(money: Money):Option[Money] = money match {
    case Money._10|Money._100|Money._1000|Money._50|Money._500 => {
      cashBox.put('dropedIn, money)
      None
    }
    case _ => Some(money)
  }
  def dropedInTotal = cashBox.balance('dropedIn)
  def sales = cashBox.balance('sales)
  def refund = cashBox.get('dropedIn, dropedInTotal)
  def fillIn(product:Product):Int = stock.fillIn(product)
  def stockCount(product:Product):Int = stock.stockCount(product)
  def stocks = stock.stocks
  def buyable(product:Product) = stockCount(product) > 0 && product.isEnough(dropedInTotal)
  def buyableProducts = stock.stocks.keys.filter(buyable _)
  def buy(product:Product) = product match {
    case x if buyable(x) => {
      stock.delivery(product)
      cashBox.put('dropedIn, product.calcChange(refund)) // ここrefundするのはイケてないなぁ
      cashBox.put('sales, product.price) // プロパティアクセスは排除したいけど、productとcashBoxは依存させたくない。どうしたものか、、、
      Some((product, refund))
    }
    case _ => None
  }
}
