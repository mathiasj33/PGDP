package pgdp.blatt05;

import pgdp.global.MiniJava;

public class FunctionalCaesar extends MiniJava {

    public static char shift(char c, int k) {
        k %= 26;
        char newChar = (char) (c + k);
        if(isUpperCase(c)) newChar = ensureBounds(newChar, 65, 90); //ASCII uppercase: 65 - 90
		else if(isLowerCase(c)) newChar = ensureBounds(newChar, 97, 122); //ACII lowercase: 97-122
		else { //not in [a-zA-Z]
			return c;
		}
        return newChar;
    }

    public static String encrypt(String s, int k) {
    	String encryptedString = "";
        for(int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	encryptedString += shift(c, k);
        }
        return encryptedString;
    }

    public static String decrypt(String s, int k) {
        return encrypt(s, -(k % 26));
    }

    public static void main(String[] args) {
        String input = readString();
        int k = read();
        String out = encrypt(input, k);
        write(out);
        
        //decryptWithAllShifts(input); -> Der Text lautet "Die gemeinsten Aufgaben stellt Raphaela"
    }

    private static boolean isUpperCase(char c) {
		return 65 <= c && c <= 90;
	}
	
	private static boolean isLowerCase(char c) {
		return 97 <= c && c <= 122;
	}
	
	private static char ensureBounds(char c, int lowerBound, int upperBound) {
		if(c > upperBound) c -= 26;
		if(c < lowerBound) c += 26;
		return c;
	}
	
	private static void decryptWithAllShifts(String s) {
		for(int i = 1; i <= 26; i++) {
			System.out.println(decrypt(s, i));
		}
	}
}

