package product

import money.Money

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/20
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
case class Product(name: String, price: Money) {
  def isEnough(money:Money) = price <= money
  def calcChange(money:Money) = money match {
    case x if x > price => x - price
    case _ => Money._0
  }
}
