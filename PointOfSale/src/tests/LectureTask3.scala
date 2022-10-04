package tests
import store.model.items.{BottleDeposit, Modifier, Sale, SalesTax}
import org.scalatest.FunSuite


class LectureTask3 extends FunSuite {
    test("LT3"){

      val epsilon = 0.001
      val sale1 = new Sale(20.0)
      assert(Math.abs(sale1.updatePrice(100.0)-80.0) < epsilon)
      assert(Math.abs(sale1.computeTax(9.0)-0.0) < epsilon)
      val salesTax1 = new SalesTax(10.0)
      assert(Math.abs(salesTax1.updatePrice(90.0)-90.0) < epsilon)
      assert(Math.abs(salesTax1.computeTax(90.0)-9.0) < epsilon)
      val bottleDeposit1 = new BottleDeposit(900.0)
      assert(Math.abs(bottleDeposit1.updatePrice(90.0)-90.0) < epsilon)
      assert(Math.abs(bottleDeposit1.computeTax(90.0)-900.0) < epsilon)



    }
}
