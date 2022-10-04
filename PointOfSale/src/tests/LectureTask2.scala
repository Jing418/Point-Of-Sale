package tests
import store.model.items.{Item, Sale, SaleTestingItem}
import org.scalatest.FunSuite

class LectureTask2 extends FunSuite{
  test("Test LT2") {
    val epsilon = 0.001

    val sale1 = new Sale(20)
    assert(Math.abs(sale1.updatePrice(100)-80.0) < epsilon)

    val sale2 = new Sale(0)
    assert(Math.abs(sale2.updatePrice(100)-100.0) < epsilon)

    val sale3 = new Sale(20)
    assert(Math.abs(sale3.updatePrice(100)-72.0) > epsilon)

    val saleLists:List[Sale] = List(sale1,sale3)
    val newSale = new SaleTestingItem("orange",100)
    for (saleList <- saleLists){
      newSale.addSale(saleList)
    }
    assert(Math.abs(newSale.price()-64.0) < epsilon)

    val item = new SaleTestingItem("description",100)
    val sale = new Sale(20)
    oob(item,sale)
    assert(Math.abs(item.price()-80.0) < epsilon)
    sale.percentOff = 60
    assert(Math.abs(item.price()-40.0) < epsilon)

  }
  def oob(item : SaleTestingItem,sale:Sale): Unit ={
    item.addSale(sale)
  }

}
