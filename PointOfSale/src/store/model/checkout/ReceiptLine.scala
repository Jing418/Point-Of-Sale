package store.model.checkout


class ReceiptLine(var description: String, var amount: Double) {

  def deep(): String = {
    this.description + "," + this.amount
  }

}
