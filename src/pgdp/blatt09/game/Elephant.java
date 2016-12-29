package pgdp.blatt09.game;

public class Elephant extends Vegetarian {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Elephant(boolean female) {
        super(female);
    }

    public Elephant(boolean female, String square, Position position) {
        super(female, square, position);
    }

    @Override
    public Move[] possibleMoves() {
        List<Move> moves = new List<>();
        for(Vector dir : Vector.FOUR_DIR_VECTORS) {
            Vector pos = VectorUtils.squareToVector(square);
            while(true) {
                pos = pos.add(dir);
                String posSquare = VectorUtils.vectorToSquare(pos);
                if(!position.isValid(posSquare) || position.fieldOccupied(posSquare)) {
                    break;
                }
                moves.add(new Move(this.square, VectorUtils.vectorToSquare(pos)));
            }
        }
        return moves.toArray(new Move[moves.size()]);
    }

    @Override
    public String toString() {
        return this.female
                ? (Globals.darkSquare(this.square) ? Globals.ts_female_elephant_dark : Globals.ts_female_elephant_light)
                : (Globals.darkSquare(this.square) ? Globals.ts_male_elephant_dark : Globals.ts_male_elephant_light);
    }

}
