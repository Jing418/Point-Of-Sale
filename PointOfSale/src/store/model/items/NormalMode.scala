package store.model.items

class NormalMode (val loyaltySale: LoyaltySale) extends ModeDecision {

  def loyalMode(): Unit={
    loyaltySale.modeDecision = new LoyalMode(loyaltySale)
  }

  def normalMode(): Unit={
    //normalMode
  }

  override def loyalPrice(normalPrice: Double, percentageSale: Double): Double={
    normalPrice
  }
}
