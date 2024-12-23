import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Assert;

public class TestSupermarket {
  @Test
  public void testAddingItems(){
    Basket basket = new Basket();
    basket.addItem("FR1");
    basket.addItem("SR1");
    basket.addItem("CF1");
    basket.addItem("SR1");
    basket.addItem("SR1");
    basket.addItem("FR1");
    Assert.assertTrue(basket.getItems().get("CF1").equals(1));
    Assert.assertTrue(basket.getItems().get("FR1").equals(2));
    Assert.assertTrue(basket.getItems().get("SR1").equals(3));
  }

  @Test
  public void testDiscounts(){
    Basket basket = new Basket();
    Basket basket1 = new Basket();
    Basket basket2 = new Basket();
    basket.addItem("FR1");
    basket.addItem("FR1");
    Assert.assertTrue(Supermarket.discount(basket).equals(new BigDecimal("3.11")));

    basket1.addItem("SR1");
    basket1.addItem("SR1");
    basket1.addItem("SR1");
    Assert.assertTrue(Supermarket.discount(basket1).equals(new BigDecimal("1.5")));

    basket2.addItem("FR1");
    basket2.addItem("FR1");
    basket2.addItem("SR1");
    basket2.addItem("SR1");
    basket2.addItem("SR1");
    basket2.addItem("CF1");
    Assert.assertTrue(Supermarket.discount(basket2).equals(new BigDecimal("4.61")));
    
    
  }

  @Test
  public void testTotal(){
    HashMap<String,Product> allItems = new HashMap<String,Product>();
    allItems.put("FR1",new Product("FR1", "Fruit tea", new BigDecimal("3.11")));
    allItems.put("SR1",new Product("SR1", "Strawberries", new BigDecimal("5.00")));
    allItems.put("CF1",new Product("CF1", "Coffee", new BigDecimal("11.23")));
    Basket basket = new Basket();
    basket.addItem("FR1");
    basket.addItem("SR1");
    basket.addItem("CF1");
    basket.addItem("SR1");
    basket.addItem("SR1");
    basket.addItem("FR1");
    Assert.assertTrue(Supermarket.calculateTotal(basket, allItems).equals(("27.84")));
  }

}
