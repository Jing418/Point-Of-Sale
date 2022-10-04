package store.model.items
import store.model.items.Sale


class SaleTestingItem (var itemdescription: String, var itemprice: Double) {

  var saleLists:List[Sale] = List()

  def addSale(sale: Sale): Unit ={
    saleLists = sale +: saleLists
  }

  def price(): Double ={
    var finalprice = itemprice
    for (salelist <- saleLists){
      finalprice = salelist.updatePrice(finalprice)
    }
    finalprice
  }

}
