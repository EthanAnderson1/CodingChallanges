import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
public class SimpleCalculator {

  public static void main(String[] args) {
    OperationStore operationStore = new OperationStore();
    //if a file has been referenced
    if(args.length == 1){
      try{
        File myObj = new File(args[0]);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String inputLine = myReader.nextLine().toLowerCase();
          String[] data = inputLine.split(" ");
          //if its a print command, evaluate the referenced register and print result
          if (data.length==2 && data[0].equals("print") && !Utils.isFloat(data[1])){
            System.out.println(operationStore.evaluate(data[1], new HashMap<String,Float>()));
          //if a valid operation is input, add it to the operation store
          }else if(Utils.isValidInput(data)){
            operationStore.add(data[0],data[1],data[2]);
          }
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.err.println("Error reading from file");
        e.printStackTrace();
      }
    }else{
      //if no file has been referenced
      Scanner userInputScanner = new Scanner(System.in);
      String input;
      System.out.println("Enter Input");
      while(userInputScanner.hasNext()){
        input = userInputScanner.nextLine();
        //close the program on 'quit' input
        if(input.toLowerCase().equals("quit")){
          userInputScanner.close();
          break;
        }
        String inputLine = input.toLowerCase();
        String[] data = inputLine.split(" ");
        //if its a print command, evaluate the referenced register and print result
        if (data.length==2 && data[0].equals("print") && !Utils.isFloat(data[1])){
          System.out.println(operationStore.evaluate(data[1], new HashMap<String,Float>()));
          //if a valid operation is input, add it to the operation store
        }else if(Utils.isValidInput(data)){
          operationStore.add(data[0],data[1],data[2]);
        }
        System.out.println("Enter Input");
      }
      userInputScanner.close();
    }
  }
}