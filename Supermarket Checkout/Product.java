import java.math.BigDecimal;

//Definition of a product
public class Product{
  String productCode;
  String name;
  BigDecimal price;

  public Product(String productCode, String name, BigDecimal price){
    this.productCode = productCode;
    this.name = name;
    this.price = price;
  }
}