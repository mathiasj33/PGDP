package pgdp.blatt08;

public class Strcmp {
    public static boolean equals(String a, String b) {
        if(a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(equals("Hallo", "Hallo"));
        System.out.println(equals("aaaas", "aaaaaas"));
    }
    
}
