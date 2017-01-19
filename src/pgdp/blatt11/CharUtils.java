package pgdp.blatt11;

public class CharUtils {
    public static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }
    
    public static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }
    
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
