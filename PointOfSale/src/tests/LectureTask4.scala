package tests
import store.model.items.{Item, Modifier, Sale, SalesTax}
import org.scalatest.FunSuite


class LectureTask4 extends FunSuite {
  test("LT4"){

    val item1 = new Item("milk",100)
    val item2= new Item("tea",120)
    val item3= new Item("orange",40)
    val item4= new Item("chocolate",1000)
    val sale1= new Sale(20)
    val sale2= new Sale(80)
    val sale3= new SalesTax(50)
    val sale4= new SalesTax(10)


    item1.addModifier(sale1)
    item1.addModifier(sale2)
    item3.addModifier(sale3)
    item3.addModifier(sale4)
    item4.addModifier(sale1)
    item4.addModifier(sale3)


    assert(compareDoubles(item1.price(),16.0))
    assert(compareDoubles(item2.price(),120.0))
    assert(compareDoubles(item3.tax(),24.0))
    assert(compareDoubles(item4.tax(),400.0))
    assert(compareDoubles(item4.price(),800.0))

  }
  val epsilon = 0.001
  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < epsilon
  }
}
