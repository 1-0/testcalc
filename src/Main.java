import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String startString = new String("Type math expression ('a+b', 'a-b', 'a*b', 'a/b') or 'exit':");
        String exitString = new String("exit");
        String exitingString = new String("Exiting...");
        String wrongString = new String("Wrong statement");
        int firstNum, secondNum;
        String operation;
        while (true) {
            System.out.println(startString);
            Scanner s = new Scanner(System.in);
            String inputStr = s.nextLine().replaceAll("\\s+","").toLowerCase();
            if ( exitString.equals(inputStr)) {
                System.out.println(exitingString);
                System.exit(0);
            }
            if(inputStr.contains("+")){
                operation = "+";
            } else if (inputStr.contains("-")) {
                operation = "-";
            } else if (inputStr.contains("*")) {
                operation = "*";
            } else if (inputStr.contains("/")) {
                operation = "/";
            } else {
                System.out.println(wrongString);
                continue;
            }
        }
    }
}
