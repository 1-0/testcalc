import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


public class Main {

    // roman-arabic converter from https://www.baeldung.com/java-convert-roman-arabic
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        String startString = new String("Type math expression ('a+b', 'a-b', 'a*b', 'a/b') or 'exit':");
        String exitString = new String("exit");
        String exitingString = new String("Exiting...");
        String wrongString = new String("Wrong statement");
        String resultString = new String("Result:");
        int firstNum = 0;
        int secondNum = 0;
        int resultNum = 0;
        String operation;
        String[] romanN = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        List romanNumbers = Arrays.asList(romanN);
        String[] arabicN = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        List arabicNumbers = Arrays.asList(arabicN);
        String numbersType;
        String parts[];


        while (true) {
            System.out.println(startString);
            Scanner s = new Scanner(System.in);
            String inputStr = s.nextLine().replaceAll("\\s+","").toUpperCase();
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
            if ( parts.length != 2 ) {
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
            switch (operation){
                case "\\+": resultNum = firstNum + secondNum;
                            break;
                case "\\-": resultNum = firstNum - secondNum;
                            break;
                case "\\*": resultNum = firstNum * secondNum;
                            break;
                case "\\/": if (secondNum==0){
                    System.out.println(wrongString);
                    continue;
                } else {
                    resultNum = firstNum / secondNum;
                }
                            break;

            }
            if (numbersType == "arabic" || resultNum<1 ) {
                System.out.println(String.format(resultString+" %d", resultNum));
            } else {
                System.out.println(String.format(resultString+" %s", arabicToRoman(resultNum)));
            }
        }
    }
}
