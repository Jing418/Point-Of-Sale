package store.model.items

abstract class ModeDecision {

  def loyalMode(): Unit

  def normalMode(): Unit

  def loyalPrice(normalPrice: Double,percentageSale: Double): Double
}
