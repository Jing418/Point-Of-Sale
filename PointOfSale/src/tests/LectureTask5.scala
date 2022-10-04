package tests
import store.model.items.Item
import org.scalatest.FunSuite
import store.model.checkout.{ReceiptLine, SelfCheckout}

class LectureTask5 extends FunSuite{


  val checkout1 = new SelfCheckout()
  val item1 = new Item("milk", 50)
  val item2 = new Item("tea", 60)
  val item3 = new Item("orange", 70)
  val item4 = new Item("chocolate", 80)

  test("LT5") {
    checkout1.addItem("123", item1)
    checkout1.addItem("124", item2)
    checkout1.addItem("125", item3)
    checkout1.addItem("126", item4)
    checkout1.numberPressed(1)
    checkout1.clearPressed()
    assert(checkout1.displayString == "")
    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    assert(checkout1.displayString == "12")
    checkout1.clearPressed()
    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(3)
    assert(checkout1.displayString == "123")
    checkout1.enterPressed()
    val epsilon = 0.001
    for ( r <- checkout1.receiptLines()){
      assert( r.description == "milk")
      assert(Math.abs(r.amount-50.0) < epsilon)
    }
    var list: List[ReceiptLine] = checkout1.receiptLines()
    checkout1.numberPressed(5)
    checkout1.numberPressed(6)
    checkout1.numberPressed(7)
    checkout1.enterPressed()
    list= checkout1.receiptLines()
    assert(list(0).description == "milk")
    assert(Math.abs(list(0).amount-50.0) < epsilon)
    assert(list(1).description == "error")
    assert(Math.abs(list(1).amount-0.0) < epsilon)


  }
}