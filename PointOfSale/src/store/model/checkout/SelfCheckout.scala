package store.model.checkout

import store.model.items.{Item, LoyaltySale, Sale, SalesTax}

class SelfCheckout {
  var errorItem: Item = new Item("error", 0.0)
  var itemStore: Map[String, Item] = Map()
  var itemNameStore: Map[String, Item] = Map()
  var shoppingCart: List[Item] = List()
  var _receiptLines: List[ReceiptLine] = List()
  var enteredBarcode: String = ""
  var _displayStr: String = "welcome to my store"
  var barcodeItem: Item = new Item("", 0.0)
  var state = new CheckOutToPay(this)

  def addItem(barcode: String, item: Item): Unit = {
    itemStore += (barcode -> item)
    itemNameStore += (item.description() -> item)
  }

  def changeDisplayString(displayStr: String): Unit = {
    _displayStr = displayStr
  }


  def numberPressed(number: Int): Unit = {
    enteredBarcode += number.toString
    changeDisplayString(enteredBarcode)
  }


  def clearPressed(): Unit = {
    enteredBarcode = ""
    changeDisplayString(enteredBarcode)
    itemStore = itemStore + ("" -> errorItem)
  }


  def enterPressed(): Unit = {

    barcodeItem = itemStore.getOrElse(enteredBarcode, errorItem)
    itemStore = itemStore + ("" -> barcodeItem)
    _receiptLines :+= new ReceiptLine(barcodeItem.description(), barcodeItem.price())
    shoppingCart = shoppingCart :+ barcodeItem
    enteredBarcode = ""
    changeDisplayString(enteredBarcode)

  }

  def checkoutPressed(): Unit = {
    var totalPrice: Double = 0
    var itemPrice: Double = 0
    var taxPrice: Double = 0
    for (shopping <- shoppingCart) {
      itemPrice += shopping.price()
      taxPrice += shopping.tax()
      totalPrice += shopping.price() + shopping.tax()
    }


    _receiptLines :+= new ReceiptLine("subtotal", itemPrice)
    _receiptLines :+= new ReceiptLine("tax", taxPrice)
    _receiptLines :+= new ReceiptLine("total", totalPrice)
    changeDisplayString("cash or credit")
    shoppingCart = List()

  }

  def cashPressed(): Unit = {
    _displayStr = this.state.displayString(_displayStr)
    reSetLoyaltyCard(itemStore)
    reSetLoyaltyCard(itemNameStore)
    _receiptLines = this.state.listOfReceiptLines(_receiptLines)
  }

  def creditPressed(): Unit = {
    _displayStr = this.state.displayString(_displayStr)
    reSetLoyaltyCard(itemStore)
    reSetLoyaltyCard(itemNameStore)
    _receiptLines = this.state.listOfReceiptLines(_receiptLines)
  }

  def loyaltyCardPressed(): Unit = {

    initLoyaltyCard(itemStore)
    _displayStr = this.state.displayString(_displayStr)
    initLoyaltyCard(itemNameStore)
    //Items in the store are in loyal sale

    var newReceiptLines: List[ReceiptLine] = List()
    var newItem: Item = new Item("", 0.0)

    for (r <- _receiptLines) {
      println("r: " + r.description.toString)
      newItem = itemNameStore.getOrElse(r.description, errorItem) //find the loyal price of the item
      println(newItem.description())
      newReceiptLines :+= new ReceiptLine(newItem.description(), newItem.price())
    }

    _receiptLines = newReceiptLines


    // TODO
  }

  def displayString(): String = {
    _displayStr
  }


  def receiptLines(): List[ReceiptLine] = {
    _receiptLines
  }

  def prepareStore(): Unit = {
    var item1 = new Item("milk", 50)
    var item2 = new Item("tea", 60)
    var item3 = new Item("orange", 70)
    var item4 = new Item("chocolate", 80)
    addItem("123", item1)
    addItem("124", item2)
    addItem("125", item3)
    addItem("126", item4)
    val sale1 = new Sale(20)
    val sale2 = new SalesTax(50)
    item1.addModifier(sale1)
    item2.addModifier(sale2)

    val loyaltySale: LoyaltySale = new LoyaltySale(30)
    item1.addModifier(loyaltySale)
  }


  def initLoyaltyCard(a: Map[String, Item]): Unit = {
    for ((barcode, value) <- itemStore) {
      var item: Item = value
      for (modifier <- item._modifiers) {
        modifier.loyalMode()
        println(modifier)
      }
    }

  }


  def reSetLoyaltyCard(a: Map[String, Item]): Unit = {
    for ((barcode, value) <- itemStore) {
      var item: Item = value
      for (modifier <- item._modifiers) {
        modifier.normalMode()
        println(modifier)
      }
    }
  }


}
