import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class OperationStore {
    //Create a store for operations to be evaluated in the future
    //Hashmap used to be able to locate only the values needed
    //the structure of this ensures operations for a register are retrieved together e.g a => [{add,1},{add,2}], b =[{subtract,2}]
    HashMap<String, ArrayList<Pair<String,String>>> operationStore = new HashMap<String, ArrayList<Pair<String,String>>>();

    //get operations store
    public HashMap<String, ArrayList<Pair<String, String>>> getOperationStore() {
      return operationStore;
    }

    //add to operations store
    public void add(String key, String operation, String value) {
      //if register is already added to the operationStore then add the operation to it
      if(this.operationStore.containsKey(key)){
        operationStore.get(key).add(new Pair<>(operation, value));
      }else{
        //add a new register to the store and add the operation to it
        ArrayList<Pair<String,String>> operations = new ArrayList<Pair<String,String>>();
        operations.add(new Pair<String,String>(operation, value));
        operationStore.put(key, operations);
      }
    }
    
    /*public void clear(){
      Out of scope 
    }*/

    //evaluate the value of a register
    public float evaluate(String key, HashMap<String,Float> bufferMap){
      //This evaluation allows for ambiguous definition and will create its own order of events
      //For example a add b, b add 1, b add a, is different depending on order of calculations. 
      //Since this is a lazy algorithm I assumed the task was to give it a go rather than throwing an ambiguous input error.
      //float is used to allow easy integration of divide function 
      float output=0;
      float value=0;
      //if register is not in the operations store return 0
      if(!operationStore.containsKey(key)){
        return 0;
      }
      //for each operation stored with the register
      for (Pair<String, String> operation : operationStore.get(key)){
        if (Utils.isFloat(operation.getValue())){
          value = Float.valueOf(operation.getValue());
        }else{
          //if the value is not a number we need to evaluate its value
          //when the operation refers to itself e.g. a add a, use the current running value
          if(key.equals(operation.getValue())){
            value = output;
          }
          //if we have evaluated the referenced register before we can find it in the buffer
          if(bufferMap.containsKey(operation.getValue())){
            value = bufferMap.get(operation.getValue());
          }else{ 
            //if it is not found in the buffer it needs to be evaluated
            //add the current value of this register to the buffer then evaluate the referenced register
            //this prevents infinite looping and will decrease cost for a larger volume of inputs
            bufferMap.put(key, output);
            value = evaluate(operation.getValue(), bufferMap);}
        }
        if(operation.getKey().equals("add")){
          output += value;
        }
        if(operation.getKey().equals("subtract")){
          output -= value;
        };
        if(operation.getKey().equals("multiply")){
          output *= value;
        }
        /* Out of scope
          if(operation.getKey().equals("divide")){
          output /= value;
        }*/
      }
      return output;
    }
}
