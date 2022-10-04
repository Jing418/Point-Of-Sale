package store.model.items

abstract class Modifier{

    def updatePrice(beforemodifier:Double ): Double

    def computeTax(finalTax: Double):Double

    def loyalMode(): Unit

    def normalMode(): Unit

}
