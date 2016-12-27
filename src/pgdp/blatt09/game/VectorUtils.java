
package pgdp.blatt09.game;

public class VectorUtils {
    public static String add(String square, Vector v) {
        Vector result = v.add(squareToVector(square));
        return vectorToSquare(result);
    }
    
    public static Vector squareToVector(String square) {
        if(square.length() != 2) throw new IllegalArgumentException("This position is not valid: " + square);
        char c0 = square.charAt(0);
        char c1 = square.charAt(1);
        return new Vector(c0 - 'a', c1 - '1');
    }
    
    public static String vectorToSquare(Vector v) {
        String result = "";
        result += (char) (v.x + 'a');
        result += (char) (v.y + '1');
        return result;
    }
}
