package pgdp.blatt04;

import pgdp.global.MiniJava;

public class Caesar extends MiniJava {
	
	public static void main(String[] args) {
		new Caesar().start();
	}
	
	public void start() {
		write(encrypt(readString("Zeichenkette, die verschl�sselt werden soll:"), read("Zyklischer Shift:")));
	}
	
	private String encrypt(String text, int shift) {
		shift %= 26;
		String encryptedString = "";
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			char newChar = (char) (c + shift);
			if(isUpperCase(c)) {
				newChar = ensureBounds(newChar, 65, 90); //ASCII uppercase: 65 - 90
				encryptedString += newChar;
			}
			else if(isLowerCase(c)) {
				newChar = ensureBounds(newChar, 97, 122); //ACII lowercase: 97-122
				encryptedString += newChar;
			}
			else { //not in [a-zA-Z]
				encryptedString += c;
				continue;
			}
		}
		return encryptedString;
	}
	
	private boolean isUpperCase(char c) {
		return 65 <= c && c <= 90;
	}
	
	private boolean isLowerCase(char c) {
		return 97 <= c && c <= 122;
	}
	
	private char ensureBounds(char c, int lowerBound, int upperBound) {
		if(c > upperBound) c -= 26;
		if(c < lowerBound) c += 26;
		return c;
	}
}
