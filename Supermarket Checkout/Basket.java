import java.util.HashMap;

public class Basket{
  
  private HashMap<String,Integer> items = new HashMap<String,Integer>();
  
  public Basket(){

  }

  public void addItem(String productCode){
    if(items.containsKey(productCode)){
      items.put(productCode, new Integer(items.get(productCode)+1));
    }else{
      items.put(productCode,1);
    }
  }

  public HashMap<String, Integer> getItems() {
    return items;
  }
}