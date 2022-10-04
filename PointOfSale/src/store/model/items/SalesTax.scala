package store.model.items

class SalesTax (percentageTax:Double)
  extends Modifier {

    def updatePrice(beforeModifier: Double): Double = {
      beforeModifier
    }

    def computeTax(finalPrice: Double): Double = {
      var output = finalPrice * this.percentageTax/100
      output
    }

    def loyalMode(): Unit={
    }

    def normalMode(): Unit={
    }
}

