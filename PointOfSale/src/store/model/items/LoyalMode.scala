package store.model.items

class LoyalMode (val loyaltySale: LoyaltySale) extends ModeDecision {

  def loyalMode(): Unit={
    //loyalMode
  }

  override def normalMode(): Unit={
    loyaltySale.modeDecision = new NormalMode(loyaltySale)
  }

  override def loyalPrice(normalPrice: Double,percentageSale: Double): Double={
    (1- percentageSale/100 ) * normalPrice
  }

}
