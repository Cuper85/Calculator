import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения для расчета");
        if (scanner.hasNextInt()) {
            throw new NumberFormatException("т.к. число не является математической операцией");
        }
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int one;
        int two;
        String operand;
        String result;
        boolean isRoman;
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        operand = detectedOperation(input);
        if (RomanNumbers.isRoman(operands[0]) && RomanNumbers.isRoman(operands[1])) {
            one = RomanNumbers.convertToArabian(operands[0]);
            two = RomanNumbers.convertToArabian(operands[1]);
            isRoman = true;
        } else if (!RomanNumbers.isRoman(operands[0]) && !RomanNumbers.isRoman(operands[1])) {
            one = Integer.parseInt(operands[0]);
            two = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new NumberFormatException("т.к. используются одновременно разные системы счисления");
        }
        if (one > 10 || two > 10) {
            throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");
        }
        int arabian = calc(one, two, operand);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            result = RomanNumbers.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectedOperation(String input) {
        if (input.contains("+"))
            return "+";
        else if (input.contains("-"))
            return "-";
        else if (input.contains("*"))
            return "*";
        else if (input.contains("/"))
            return "/";
        else
            return null;
    }
    static int calc(int a, int b, String operand) {
        if (operand.equals("/")) {
            if (b == 0) {
                try {
                    throw new Exception("т.к. нельзя делить на ноль");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return a / b;
        } else if (operand.equals("-")) {
           return a - b;
        } else if (operand.equals("*")) {
            return a * b;
        } else {
            return a + b; }
    }

    class RomanNumbers  {
        static String[] romanArray = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        public static boolean isRoman(String value) {
            for (int i = 0; i < romanArray.length; i++) {
                if (value.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }
        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }
        public static String convertToRoman(int arabian) {

            return romanArray[arabian];
        }
    }
}