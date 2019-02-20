import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String startString = new String("Type math expression ('a+b', 'a-b', 'a*b', 'a/b') or 'exit':");
        String exitString = new String("exit");
        String exitingString = new String("Exiting...");
        String wrongString = new String("Wrong statement");
        int firstNum, secondNum, resNum;
        String operation;
        String[] romanN = {"i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x"};
        List romanNumbers = Arrays.asList(romanN);
        String[] arabicN = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        List arabicNumbers = Arrays.asList(arabicN);
        String numbersType;
        String parts[];


        while (true) {
            System.out.println(startString);
            Scanner s = new Scanner(System.in);
            String inputStr = s.nextLine().replaceAll("\\s+","").toLowerCase();
            if ( exitString.equals(inputStr)) {
                System.out.println(exitingString);
                System.exit(0);
            }
            if(inputStr.contains("+")){
                operation = "\\+";
            } else if (inputStr.contains("-")) {
                operation = "\\-";
            } else if (inputStr.contains("*")) {
                operation = "\\*";
            } else if (inputStr.contains("/")) {
                operation = "\\/";
            } else {
                System.out.println(wrongString);
                continue;
            }
            parts = inputStr.split(operation);
            if ( parts.length > 2 ) {
                System.out.println(wrongString);
                continue;
            }
            if (romanNumbers.contains(parts[0]) && romanNumbers.contains(parts[1])) {
                numbersType = "roman";
                firstNum = romanNumbers.indexOf(parts[0]) + 1;
                secondNum = romanNumbers.indexOf(parts[1]) + 1;
            } else if (arabicNumbers.contains(parts[0]) && arabicNumbers.contains(parts[1])) {
                numbersType = "arabic";
                firstNum = arabicNumbers.indexOf(parts[0]);
                secondNum = arabicNumbers.indexOf(parts[1]);
            } else {
                System.out.println(wrongString);
                continue;
            }
        }
    }
}
