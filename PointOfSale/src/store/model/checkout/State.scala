package store.model.checkout

abstract class State {

  def displayString(string: String): String
  def listOfReceiptLines(list: List[ReceiptLine]): List[ReceiptLine]

}
