package store.model.items

class BottleDeposit (var totalDeposit : Double)
  extends Modifier{

  override def updatePrice(beforeModifier: Double): Double = {
    beforeModifier
  }

  override def computeTax(finalTax: Double): Double = {
    totalDeposit
  }

  override def loyalMode(): Unit={
  }

  override def normalMode(): Unit={
  }


}
