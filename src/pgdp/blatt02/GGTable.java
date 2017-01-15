package pgdp.blatt02;

import pgdp.global.MiniJava;

public class GGTable extends MiniJava {

    public static void main(String[] args) {
        new GGTable().start();
    }

    public void start() {
        int n = readInt("Geben Sie die Größe ein: ");
        if (n <= 0) {
            write("Fehlerhafte Eingabe.");
            return;
        }

        printUpperNumbers(n);
        printLines(n);
    }

    private void printUpperNumbers(int n) {
        String upperNumbers = "\t";
        for (int i = 1; i <= n; i++) {
            upperNumbers += i + "\t";
            if (i == n)
                System.out.println(upperNumbers);
        }
    }

    private void printLines(int n) {
        for (int i = 1; i <= n; i++) {
            String line = i + "\t";
            for (int j = 1; j <= n; j++) {
                line += ggt(i, j) + "\t";
                if (j == n)
                    System.out.println(line);
            }
        }
    }

    private int ggt(int a, int b) {
        if (a == b)
            return a;
        else if (a > b)
            return ggt(a - b, b);
        else
            return ggt(b - a, a);
    }
}
