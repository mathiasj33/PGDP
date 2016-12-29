package pgdp.blatt09.game;

public class Vector {

    public final int x;
    public final int y;
    public final static Vector[] FOUR_DIR_VECTORS = new Vector[]{
        new Vector(1, 0), new Vector(0, -1), new Vector(-1, 0), new Vector(0, 1)
    };
    public final static Vector[] EIGHT_DIR_VECTORS = new Vector[]{
        new Vector(0, 1), new Vector(0, -1), new Vector(1, 0), new Vector(-1, 0), new Vector(1, 1), new Vector(-1, 1), new Vector(1, -1), new Vector(-1, -1)
    };

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector mult(int scalar) {
        return new Vector(x * scalar, y * scalar);
    }
    
    @Override
    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        hashCode = hashCode * prime + x;
        hashCode = hashCode * prime + y;
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vector other = (Vector) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
}
