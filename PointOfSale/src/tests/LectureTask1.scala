package tests

import store.model.items.Item

import org.scalatest.FunSuite

class LectureTask1 extends FunSuite {

  test("Test LT1") {

    val epsilon = 0.001

    val item = new Item("milk", 10.0)
    assert(item.description == "milk")
    assert(Math.abs(item.price-10.0) < epsilon)
    item.scanned()
    item.scanned()
    item.scanned()
    assert(item.timesScanned == 3)

    val item2 = new Item("tea", 2.9)
    assert(item2.description == "tea")
    assert(Math.abs(item2.price-3.5) > epsilon)
    item2.scanned()
    assert(item2.timesScanned == 1)

    val item3 = new Item("orange", 0.0)
    assert(item3.description == "orange")
    assert(Math.abs(item3.price - 0.0) < epsilon)
    assert(item3.timesScanned == 0)

  }



}
