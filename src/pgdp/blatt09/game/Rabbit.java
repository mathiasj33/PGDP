package pgdp.blatt09.game;

public class Rabbit extends Vegetarian {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Rabbit(boolean female) {
        super(female);
    }

    public Rabbit(boolean female, String square, Position position) {
        super(female, square, position);
    }

    @Override
    public Move[] possibleMoves() {
        List<Move> moves = new List<>();
        for (Vector dir : Vector.EIGHT_DIR_VECTORS) {
            String field = VectorUtils.add(square, dir);
            if (position.isValid(field) && !position.fieldOccupied(field)) {
                moves.add(new Move(square, field));
            }
        }
        return moves.toArray(new Move[moves.size()]);
    }

    @Override
    public String toString() {
        return this.female
                ? (Globals.darkSquare(this.square) ? Globals.ts_female_rabbit_dark : Globals.ts_female_rabbit_light)
                : (Globals.darkSquare(this.square) ? Globals.ts_male_rabbit_dark : Globals.ts_male_rabbit_light);
    }

}
