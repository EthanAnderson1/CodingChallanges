import java.io.PrintStream;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import org.junit.*;

/**
 * TestSuite
 */
public class TestSuite {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void test1(){
    String[] textFile = {"test1.txt"};
    SimpleCalculator.main(textFile);
    assertEquals("5.0\r\n3.0\r\n6.0", outContent.toString().trim());
  }
  @Test
  public void test2(){
    String[] textFile = {"test2.txt"};
    SimpleCalculator.main(textFile);
    assertEquals("11.0", outContent.toString().trim());
  }
  @Test
  public void test3(){
    String[] textFile = {"test3.txt"};
    SimpleCalculator.main(textFile);
    assertEquals("90.0", outContent.toString().trim());
  }
  @Test
  public void selfReference(){
    OperationStore operationStore = new OperationStore();
    operationStore.add("a","add","a");
    assertEquals(0, operationStore.evaluate("a", new HashMap<String,Float>()));
  }
  @Test
  public void loop(){
    OperationStore operationStore = new OperationStore();
    operationStore.add("a","add","1");
    operationStore.add("b","add","a");
    operationStore.add("a","add","b");
    assertEquals(2, operationStore.evaluate("a", new HashMap<String,Float>()));
    assertEquals(1, operationStore.evaluate("b", new HashMap<String,Float>()));
  }
  @Test
  public void invalidRegister(){
    String[] data = {"1","add","1"};
    Utils.isValidInput(data);
    assertEquals("Invalid Register Name: 1 add 1", errContent.toString().trim());
  }
  @Test
  public void invalidNumberOfInputs(){
    String[] data1 = {"a","add","1","add","1"};
    Utils.isValidInput(data1);
    assertEquals("Invalid Number of Inputs: a add 1 add 1", errContent.toString().trim());
  }
  @Test
  public void invalidOperator(){
    String[] data2 = {"a","plus","1"};
    Utils.isValidInput(data2);
    assertEquals("Invalid Operator: a plus 1", errContent.toString().trim());
  }
}