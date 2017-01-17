package pgdp.blatt09.game;

/**
 * Die Klasse IO enthaelt Hilfsprogramme zum Einlesen.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {

    public static String readString(String msg) {
        System.out.print(msg);
        return (new Scanner (System.in)).nextLine();
    }

    public static int readInt(String msg, int low, int high) {
        int result;
        do {
            System.out.print(msg);
            result = (new Scanner (System.in)).nextInt();
        } while (result < low || result > high);
        return result;
    }

    public static int readInt() {
        return (new Scanner (System.in)).nextInt();
    }

    public static int readInt(String msg) {
        System.out.print(msg);
        return readInt();
    }

    public static int readIntSafe(String msg, int low, int high) {
        try {
            return readInt(msg, low, high);
        } catch (InputMismatchException e) {
            return readIntSafe(msg, low, high);
        }
    }
}
