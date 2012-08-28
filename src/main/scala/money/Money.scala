package money

/**
 * Created with IntelliJ IDEA.
 * User: natsuki
 * Date: 12/08/19
 * Time: 1:15
 * To change this template use File | Settings | File Templates.
 */
case class Money(private val amount:Int) extends Ordered[Money] {
  def +(addend:Money) = Money(amount+addend.amount)
  def -(subtrahend:Money) = Money(amount-subtrahend.amount)
  def compare(other:Money) = amount - other.amount
}

object Money {
  lazy val _0 = Money(0)
  lazy val _1 = Money(1)
  lazy val _5 = Money(5)
  lazy val _10 = Money(10)
  lazy val _50 = Money(50)
  lazy val _100 = Money(100)
  lazy val _500 = Money(500)
  lazy val _1000 = Money(1000)
  lazy val _2000 = Money(2000)
  lazy val _5000 = Money(5000)
  lazy val _10000 = Money(10000)
}
