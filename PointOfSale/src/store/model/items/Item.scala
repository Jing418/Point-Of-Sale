package store.model.items


class Item(var itemdescription: String, var itemprice: Double) {

  var _modifiers:List[Modifier] = List()

  def deep(): String = {
    this.itemdescription + "," + this.itemprice
  }

  def addModifier(modifier:Modifier): Unit ={
    _modifiers= modifier +: _modifiers
  }

  def price(): Double = {
    var finalPrice: Double = this.itemprice
    for (_modifier<-_modifiers){
      println("before: "+ finalPrice.toString)
      finalPrice = _modifier.updatePrice(finalPrice)
      println("after: "+ finalPrice.toString)
    }
    finalPrice
  }


  def tax():Double = {
    var finalPrice: Double = price()
    var finalTax : Double = 0
    for (_modifier<-_modifiers){
      finalTax = finalTax + _modifier.computeTax(finalPrice)
    }
    finalTax
  }

  def description(): String = {
    this.itemdescription
  }

  var timesscanned: Int = 0

  def scanned(): Unit = {
    timesscanned += 1
  }

  def timesScanned(): Int = {
    timesscanned
  }

}
