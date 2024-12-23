import java.math.BigDecimal;
import java.util.HashMap;

public class Supermarket{
 
  public static void main(String[] args){
    //List of items
    HashMap<String,Product> allItems = new HashMap<String,Product>();
    allItems.put("FR1",new Product("FR1", "Fruit tea", new BigDecimal("3.11")));
    allItems.put("SR1",new Product("SR1", "Strawberries", new BigDecimal("5.00")));
    allItems.put("CF1",new Product("CF1", "Coffee", new BigDecimal("11.23")));

    //Scan Items
    Basket basket = new Basket();
    for(String productCode: args){
      if(allItems.containsKey(productCode)){
        basket.addItem(productCode);
      }
    }

    calculateTotal(basket,allItems);
  }

  public static String calculateTotal(Basket basket, HashMap<String,Product> allItems){
    //Sets the total cost to 0
    //Then iterates through the items in the basket adding the cost
    BigDecimal total = new BigDecimal(0);
    HashMap<String,Integer> items = basket.getItems();
    for(String productCode:items.keySet()){
      total = total.add(allItems.get(productCode).price.multiply(new BigDecimal(items.get(productCode))));
    }

    //Calculate discount
    System.out.println("Savings: "+ discount(basket).toString());
    total = total.subtract(discount(basket));

    //Print and return total
    System.out.println("Total price: "+total.toString());
    return total.toString();
  }

  public static BigDecimal discount(Basket basket){
    //logic for calculating discounts
    BigDecimal discount = new BigDecimal(0);
    HashMap<String,Integer> items = basket.getItems();
    if(items.containsKey("FR1")&&items.get("FR1")>1){
      discount = discount.add(new BigDecimal("3.11").multiply(new BigDecimal(Math.floorDiv(items.get("FR1"), 2))));
    }
    
    if(items.containsKey("SR1")&&items.get("SR1")>2){
      discount = discount.add(new BigDecimal("0.5").multiply(new BigDecimal(items.get("SR1"))));
    }
    return discount;
  }
}

//Considerations
//HashMap used instead of Arraylist, imporve scalability. Taking into account searching/modifying Hashmap.
//Big deciaml used instead of float. This is to avoid numbers that cannot be represented by float.
//All offers are in there own function so it's easily added too

//Improvements
//Remove all values and declare them as constants. This prevents spelling errors when repeating values
//There is no way to remove items from the basket.
//A print inventory function 
//error handling for unknown product codes
//Add functionality to add items and discounts at runtime