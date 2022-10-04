package store.model.checkout

class CheckOutToPay(selfCheckout: SelfCheckout) extends State {
  override def displayString (string: String): String = {
    "thank you"
  }

 var _list : List[ReceiptLine] = List()
  override def listOfReceiptLines(list: List[ReceiptLine]): List[ReceiptLine] = {
    _list
  }
}
