package pgdp.blatt04;

import pgdp.global.MiniJava;

public class Verhext extends MiniJava {

    // x^y
    public static int pow(int x, int y) {
        return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
    }

    public static void main(String[] args) {
        String input = readString();
        int output;

        if (!isValid(input)) {
            write("Ung�ltige Eingabe.");
            return;
        }

        String cleanedInput = cleanInput(input);
        boolean negative = input.charAt(0) == '-';
        output = calculateHexValue(cleanedInput, negative);
        write(output);
    }

    private static boolean isValid(String input) {
        if (!isPrefixValid(input))
            return false;

        boolean negative = input.charAt(0) == '-';
        int prefixEnd = negative ? 3 : 2;

        if (input.length() <= prefixEnd)
            return false; //ensure that there is at least one digit

        for (int i = prefixEnd; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!containedIn("01234565789ABCDEFabcdef_", c))
                return false;
        }
        return true;
    }

    private static boolean isPrefixValid(String input) {
        return startsWith(input, "-0x") || startsWith(input, "-0X") || startsWith(input, "0x")
                || startsWith(input, "0X");
    }

    private static boolean startsWith(String input, String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (input.charAt(i) != pattern.charAt(i))
                return false;
        }
        return true;
    }

    private static boolean containedIn(String pattern, char c) {
        for (int i = 0; i < pattern.length(); i++) {
            if (c == pattern.charAt(i))
                return true;
        }
        return false;
    }

    public static String cleanInput(String input) {
        boolean negative = input.charAt(0) == '-';
        String cleanedInput = "";

        int prefixEnd = negative ? 3 : 2;
        for (int i = prefixEnd; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != '_')
                cleanedInput += c;
        }
        return cleanedInput;
    }

    public static int calculateHexValue(String cleanedInput, boolean negative) {
        int output = 0;

        for (int i = 0; i < cleanedInput.length(); i++) {
            int digit = getDigit(cleanedInput.charAt(i));
            output += digit * pow(16, cleanedInput.length() - (i + 1));
        }

        if (negative)
            output *= -1;

        return output;
    }

    public static int getDigit(char c) {
        if (48 <= c && c <= 57) { //0-9
            return c - 48;
        } else if (65 <= c && c <= 70) { //A-F
            return c - 55;
        } else if (97 <= c && c <= 102) { //a-f
            return c - 87;
        } else {
            throw new IllegalArgumentException("Value not in [0-9a-fA-F]");
        }
    }
}
