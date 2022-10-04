package tests

import org.scalatest.FunSuite
import store.model.checkout.{ReceiptLine, SelfCheckout}
import store.model.items.{Item, LoyaltySale, Sale, SalesTax}

class ApplicationObjective1 extends FunSuite{

  test("AO1") {
    val epsilon : Double = 0.001
    val checkout1 = new SelfCheckout()
    val item1 = new Item("milk", 50)
    val item2 = new Item("tea", 60)
    val item3 = new Item("orange", 70)
    val item4 = new Item("chocolate", 80)
    val sale1 = new Sale(20)

    checkout1.addItem("123", item1)
    checkout1.addItem("124", item2)
    checkout1.addItem("125", item3)
    checkout1.addItem("126", item4)
    item1.addModifier(sale1)

    val loyaltySale = new LoyaltySale(30)
    item1.addModifier(loyaltySale)

    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(3)
    checkout1.enterPressed()

    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(4)
    checkout1.enterPressed()

    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(4)
    checkout1.enterPressed()

    val value1 : Double = checkout1.receiptLines()(0).amount
    assert(Math.abs(value1 - 40) < epsilon)//first item

    checkout1.checkoutPressed()

    val value2 : Double = checkout1.receiptLines()(0).amount
    assert(Math.abs(value2 - 40) < epsilon)//first item

    val value3 : Double = checkout1.receiptLines()(3).amount
    assert(Math.abs(value3 - 160) < epsilon)//subtotal

    checkout1.loyaltyCardPressed()

    val value4: Double = checkout1.receiptLines()(0).amount
    println(value4)
    assert(Math.abs(value4 - 28) < epsilon)

    checkout1.loyaltyCardPressed()

    val value5: Double = checkout1.receiptLines()(0).amount
    assert(Math.abs(value5 - 28) < epsilon)

    checkout1.cashPressed()
    assert(checkout1.receiptLines.isEmpty)

    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(3)
    checkout1.enterPressed()
    val value6 : Double = checkout1.receiptLines()(0).amount
    //assert(Math.abs(value6 - 40) < epsilon)//first item


  }
}
