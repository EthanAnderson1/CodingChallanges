public class Main {
    public static void main(String[] args) {
        String s = "1+2*5/3+6/4*2";
        System.out.println("Result = "+  process(process(process(process(s.replace(" ", ""),"/"),"*"),"-"),"+"));
    }
    public static String process(String input, String operator){
        while(input.contains(operator)) {
            int indexOfDivide= input.substring(1).indexOf(operator)+1;

            int endIndex = input.length()-1;
            int startIndex = 0;
            for (int i = indexOfDivide+1; i<input.length() && Character.isDigit(input.charAt(i)); i++) {
                endIndex = i;
            }
            for (int i = indexOfDivide - 1; i>=0 && Character.isDigit(input.charAt(i)); i--) {
                startIndex = i;
            }
            String calculation = input.substring(startIndex, endIndex+1);
            String[] calcArray;
            if(!calculation.contains(operator)){
                break;
            }
            if(operator.contains("+")){
                calcArray = calculation.split("[+]"); ;
            }else if(operator.contains("*")){
                calcArray = calculation.split("[*]"); ;
            }else {
                calcArray = calculation.split(operator);
            }

            if(calcArray[0].isEmpty()){
                break;
            }

            int x = Integer.valueOf(calcArray[calcArray.length-2]);
            int y = Integer.valueOf(calcArray[calcArray.length-1]);

            if(operator.contains("/")){
                input = input.replaceFirst(calculation,String.valueOf((int) x/y));
            }

            if(operator.contains("*")){
                calculation=x+"[*]"+y;
                input = input.replaceFirst(calculation,String.valueOf((int) x*y));
            }

            if(operator.contains("-")){
                if(startIndex>0&& input.charAt(startIndex-1)=='-') {
                    input = input.replaceFirst(calculation,String.valueOf((int) x+y));
                }else{
                    input = input.replaceFirst(calculation,String.valueOf((int) x-y));
                }
                if(input.matches("-\\d*")&&input.split("-").length==2){
                    break;
                }

            }
            if(operator.contains("+")){
                calculation=x+"[+]"+y;
                if(startIndex>0&& input.charAt(startIndex-1)=='-') {
                    input = input.replaceFirst(calculation,String.valueOf((int) x-y));

                }else{
                    input = input.replaceFirst(calculation,String.valueOf((int) x+y));
                }

            }
            input = input.replaceFirst("[+]-","-");
            input = input.replaceFirst("--","+");
        }
        return input;
    }
}