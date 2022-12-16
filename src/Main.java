import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static final char[] actions = {'+', '-', '/', '*'};

    public static void main(String[] args) {
        Converter converter = new Converter();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение");
        String operation = scan.nextLine();
        if (!validate(operation)) {
            return;
        }
        String result = calc(operation);
        //System.out.println(result);
    }

    public static String calc(String input) {
        String action = "";
        Integer result = 0;
        // какое действие нужно
        for (char c : actions) {
            if (input.indexOf(c) >= 0) {
                action = String.valueOf(c);
                break;
            }
        }
        String a = "\\"+action;
        String[] partsLeftRight = input.split("\\" + action);
        if (Converter.isRoman(partsLeftRight[0].replaceAll(" ","")) == Converter.isRoman(partsLeftRight[1].replaceAll(" ",""))) {
            int left, right;
            boolean isRoman = Converter.isRoman(partsLeftRight[0]);
            if (isRoman) {
                left = Converter.romanToInt(partsLeftRight[0].replaceAll(" ",""));
                right = Converter.romanToInt(partsLeftRight[1].replaceAll(" ",""));
            } else {
                left = Integer.parseInt(partsLeftRight[0].replaceAll(" ",""));
                right = Integer.parseInt(partsLeftRight[1].replaceAll(" ", ""));
            }
                switch (action) {
                    case "+":
                        result = left + right;
                        break;
                    case "-":
                        result = left - right;
                        break;
                    case "*":
                        result = left * right;
                        break;
                    default:
                        result = left / right;
                        break;
                }
                if (isRoman) {
                    System.out.println(Converter.intToRoman(result));
                } else {
                    System.out.println(result);
                }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
        return result.toString();
    }

    public static boolean validate(String input) {
        int actionCount = 0;
        // пробег по инпуту
        for (int i = 0; i < input.length(); i++) {
            // Пробег по action
            for (int j = 0; j < actions.length; j++) {
                // сравниваем символ и инпута
                if (input.charAt(i) == actions[j]) {
                    actionCount++;
                }

            }
        }
        if (actionCount == 0) {
            System.out.println("Действие не подходит");
            return false;
        } else if (actionCount > 1) {
            System.out.println("Введено больше одного действия");
            return false;
        }
        return true;
    }
}