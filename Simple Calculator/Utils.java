import java.util.ArrayList;

public class Utils {

  public static boolean isFloat(String input) {
    //check if the input is a number
    try { 
        Float.parseFloat(input); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    return true;
  }

  //check if input is a valid input
  public static Boolean isValidInput(String[] data) {
    final ArrayList<String> validOperators = new ArrayList<String>();
      validOperators.add("add");
      validOperators.add("subtract");
      validOperators.add("multiply");
      //validOperators.add("divide"); out of scope

    if(data.length!=3){
      System.err.println("Invalid Number of Inputs: "+ String.join(" ", data));
      return false;
    }else if(!validOperators.contains(data[1])){
      System.err.println("Invalid Operator: "+ String.join(" ", data));
      return false;
    }else if(Utils.isFloat(data[0])){
      System.err.println("Invalid Register Name: "+ String.join(" ", data));
      return false;
    }
    return true;
  }
}
