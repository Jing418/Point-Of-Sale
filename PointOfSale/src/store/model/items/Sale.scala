package store.model.items

class Sale(var percentOff: Double)
  extends Modifier{

  def updatePrice(beforeSale:Double):Double={
    val afterSale: Double = (1- this.percentOff/100 ) * beforeSale
    afterSale
  }

  def computeTax(finalPrice: Double):Double ={
    0
  }

  def loyalMode(): Unit={

  }

  def normalMode(): Unit={

  }

}
