package pgdp.blatt09.game;

public class Snake extends Predator {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Snake(boolean female) {
        super(female);
        withoutFood = 9;
    }

    public Snake(boolean female, String square, Position position) {
        super(female, square, position);
        withoutFood = 9;
    }

    @Override
    public void resetWithoutFood() {
        withoutFood = 9;
    }
    
    @Override
    public Move[] possibleMoves() {
        List<Move> moves = new List<>();
        List<Vector> vectors = getAllVectors();
        for (int i = 0; i < vectors.size(); i++) {
            moves.add(new Move(square, VectorUtils.vectorToSquare(vectors.get(i))));
        }
        return moves.toArray(new Move[moves.size()]);
    }

    private List<Vector> getAllVectors() {
        List<Vector> vectors = new List<>();
        Vector start = VectorUtils.squareToVector(square);
        
        vectors.addAll(getAlternatingVectors(start, new Vector(1, 1), false));
        vectors.addAll(getAlternatingVectors(start, new Vector(1, -1), true));
        vectors.addAll(getAlternatingVectors(start, new Vector(-1, -1), false));
        vectors.addAll(getAlternatingVectors(start, new Vector(-1, 1), true));

        return vectors;
    }

    private List<Vector> getAlternatingVectors(Vector start, Vector dir, boolean alternateX) {
        List<Vector> vectors = new List<>();
        while (true) {
            start = start.add(dir);
            String square = VectorUtils.vectorToSquare(start);
            if (!position.isValid(square)) {
                return vectors;
            } else if (position.fieldOccupied(square)) {
                if (enemyVegetarianOnField(square)) {
                    vectors.add(start);
                }
                return vectors;
            }

            vectors.add(start);
            dir = alternateX ? new Vector(-dir.x, dir.y) : new Vector(dir.x, -dir.y);
        }
    }

    @Override
    public String toString() {
        return this.female
                ? (Globals.darkSquare(this.square) ? Globals.ts_female_snake_dark : Globals.ts_female_snake_light)
                : (Globals.darkSquare(this.square) ? Globals.ts_male_snake_dark : Globals.ts_male_snake_light);
    }

}
