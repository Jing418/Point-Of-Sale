package tests
import store.model.items.{Item, Sale, SalesTax}
import org.scalatest.FunSuite
import store.model.checkout.{ReceiptLine, SelfCheckout}


class LectureTask6 extends FunSuite{
  val checkout1 = new SelfCheckout()
  val item1 = new Item("milk", 50)
  val item2 = new Item("tea", 60)
  val item3 = new Item("orange", 70)
  val item4 = new Item("chocolate", 80)
  val sale1 = new Sale(20)
  val sale2 = new SalesTax(20)
  /*val receiptLine1 = new ReceiptLine("milk", 40)
  val receiptLine2 = new ReceiptLine("error", 0.0)
  val receiptLine3 = new ReceiptLine("tea", 60.0)
  val receiptLine4 = new ReceiptLine("subtotal", 180.0)
  val receiptLine5 = new ReceiptLine("tax", 24.0)
  val receiptLine6 = new ReceiptLine("total", 204.0)*/
  val epsilon = 0.001
  test("LT6") {
    checkout1.addItem("123", item1)
    checkout1.addItem("124", item2)
    checkout1.addItem("125", item3)
    checkout1.addItem("126", item4)
    item1.addModifier(sale1)
    item1.addModifier(sale2)
    assert(checkout1.displayString().contains("welcome"))

    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(3)
    checkout1.enterPressed()
    checkout1.enterPressed()
    checkout1.enterPressed()
    //compare list assert(checkout1.receiptLine == List(receiptLine1,receiptLine1,receiptLine1))
    for ( r <- checkout1.receiptLines()){
      assert( r.description == "milk")
      assert(Math.abs(r.amount-40.0) < epsilon)
    }
    checkout1.numberPressed(5)
    checkout1.clearPressed()
    checkout1.enterPressed()
    //assert(compares(checkout1.receiptLines(),List(receiptLine1,receiptLine1,receiptLine1,receiptLine2)))
    assert(checkout1.receiptLines().last.description == "error")
    assert(Math.abs(checkout1.receiptLines().last.amount-0.0) < epsilon)
    checkout1.numberPressed(1)
    checkout1.numberPressed(2)
    checkout1.numberPressed(4)
    checkout1.enterPressed()
    //assert(compares(checkout1.receiptLines(),List(receiptLine1,receiptLine1,receiptLine1,receiptLine2,receiptLine3)))
    assert(checkout1.receiptLines().last.description == "tea")
    assert(Math.abs(checkout1.receiptLines().last.amount-60.0) < epsilon)
    checkout1.checkoutPressed()
    assert (checkout1.displayString()=="cash or credit")
    //assert(compares(checkout1.receiptLines(),List(receiptLine1,receiptLine1,receiptLine1,receiptLine2,receiptLine3,receiptLine4,receiptLine5,receiptLine6)))
    val finalList = checkout1.receiptLines()
    assert (finalList(5).description == "subtotal")
    assert (Math.abs(finalList(5).amount-180.0) < epsilon)
    assert (finalList(6).description == "tax")
    assert (Math.abs(finalList(6).amount-24.0) < epsilon)
    assert (finalList(7).description == "total")
    assert (Math.abs(finalList(7).amount-204.0) < epsilon)
    assert (Math.abs(finalList(7).amount-finalList(6).amount-finalList(5).amount)< epsilon)
    checkout1.cashPressed()
    assert (checkout1.displayString().contains("thank you"))
  }
  /*def compares(l1:List[ReceiptLine],l2: List[ReceiptLine]): Boolean ={
    var same : Boolean = true
    var size : Int = math.min(l1.size,l2.size)
    for ( i <- 0 to size-1 ){
      same = same && (l1(i).deep() == l2(i).deep())
    }
    same
  }*/



}
