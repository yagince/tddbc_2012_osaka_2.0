package dispenser

import scala.collection.mutable.{Map => MutableMap}
import product.Product

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/28
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
class Stock {

  private val _stocks = MutableMap[Product, Int]()

  def fillIn(product:Product) = {
    _stocks(product) = stockCount(product) + 1
    _stocks(product)
  }

  def delivery(product:Product) = product match {
    case x if stockCount(x) > 0 => Some(x)
    case _ => None
  }

  def stockCount(product:Product) = _stocks.get(product).getOrElse(0)
  def stocks = _stocks.toMap
}
