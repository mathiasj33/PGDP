package pgdp.blatt09.game;

/**
 * Die Klasse Move repraesentiert einen einzelnen Zug.
 *
 * Es gibt zwei Konstruktoren. Einer bekommt Ausgangsfeld und Zielfeld
 * uebergeben, der andere bekommt nur den eingegebenen Zug in der Form
 * <Ausgangsfeld><Zielfeld> als String uebergeben, also z. B. "a7c5" fuer den
 * Zug von "a7" nach "c5".
 */
public class Move {

    private String from;
    private String to;

    public Move(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public Move(String move) {
        if (move.length() != 4) {
            throw new IllegalArgumentException("The move is not valid: " + move);
        }
        from = "";
        to = "";
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                from += move.charAt(i);
            } else {
                to += move.charAt(i);
            }
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "From " + from + " to " + to;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        Move o = (Move) other;
        if (from == null) {
            if (o.from != null) {
                return false;
            }
        } else if (!from.equals(o.from)) {
            return false;
        }
        if (to == null) {
            if (o.to != null) {
                return false;
            }
        } else if (!to.equals(o.to)) {
            return false;
        }
        return true;
    }

}
