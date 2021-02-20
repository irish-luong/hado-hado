package learning
/*
This package to experience Scala annotation by examples
* @param num  Numerator
* @param denom Denominator
* @throws ArithmeticException in `case` denom is `0`
*/
class Division protected (num: Int, denom: Int = 0) {

  private[this] val wrongValue = num / denom

}
object Division extends App {

  def apply(num: Int) = new Division(num)

  apply(1)
}

