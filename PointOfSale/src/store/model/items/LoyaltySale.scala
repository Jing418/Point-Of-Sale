package store.model.items

class LoyaltySale (val percentageSale: Double) extends Modifier {

  var modeDecision : ModeDecision = new NormalMode(this)

  override def updatePrice(beforeModifier:Double ): Double ={
    println(beforeModifier)
    var a : Double =this.modeDecision.loyalPrice(beforeModifier,percentageSale)
    println(a)
    a
  }

  override def computeTax(finalTax: Double):Double ={
    0
  }

  override def loyalMode(): Unit = {
    this.modeDecision.loyalMode()
  }

  override def normalMode(): Unit ={
    this.modeDecision.normalMode()
  }

}
