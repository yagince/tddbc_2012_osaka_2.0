package money

import collection.mutable.{Map => MutableMap}

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/28
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
class CashBox {
  private val moneys = MutableMap[Symbol, Money]()

  def put(category:Symbol, money:Money) = {
    moneys(category) = (_get(category) + money)
    moneys(category)
  }
  def get(category:Symbol, money:Money) = money match {
    case x if x <= _get(category) => {
      moneys(category) = (_get(category)-money)
      money
    }
    case _ => Money._0
  }
  def balance(category:Symbol) = _get(category)

  private def _get(category:Symbol) = moneys.get(category).getOrElse{
    moneys(category) = Money._0
    moneys(category)
  }
}
