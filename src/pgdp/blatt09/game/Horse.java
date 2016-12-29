package pgdp.blatt09.game;

public class Horse extends Vegetarian {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Horse(boolean female) {
        super(female);
    }

    public Horse(boolean female, String square, Position position) {
        super(female, square, position);
    }

    @Override
    public Move[] possibleMoves() {
        List<Move> moves = new List<>();
        Vector pos = VectorUtils.squareToVector(square);
        
        for (Vector dir : Vector.FOUR_DIR_VECTORS) {
            Vector target = pos.add(dir);
            addToMovesIfValid(moves, target);
        }

        Vector[] diagonals = new Vector[]{
            new Vector(1, 1), new Vector(-1, 1), new Vector(1, -1), new Vector(-1, -1)
        };
        for (Vector dir : diagonals) {
            for (int i = 2; i <= 3; i++) {
                Vector target = pos.add(dir.mult(i));
                addToMovesIfValid(moves, target);
            }
        }
        return moves.toArray(new Move[moves.size()]);
    }

    private void addToMovesIfValid(List<Move> moves, Vector target) {
        String targetSquare = VectorUtils.vectorToSquare(target);
        if (position.isValid(targetSquare) && !position.fieldOccupied(targetSquare)) {
            moves.add(new Move(square, targetSquare));
        }
    }

    @Override
    public String toString() {
        return this.female
                ? (Globals.darkSquare(this.square) ? Globals.ts_female_horse_dark : Globals.ts_female_horse_light)
                : (Globals.darkSquare(this.square) ? Globals.ts_male_horse_dark : Globals.ts_male_horse_light);
    }

}
