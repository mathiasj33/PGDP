package pgdp.blatt09.game;

public class Penguin extends Predator {

    // Ein Pinguin kann 12 Tage bzw. Spielrunden ohne Essen auskommen.
    // Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
    // verwendet) werden.
    private static int withoutFood = 12;


    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Penguin(boolean female) {
        super(female);
    }

    public Penguin(boolean female, String square, Position position) {
        super(female, square, position);
    }

    @Override
    public Move[] possibleMoves() {
        List<Move> moves = new List<>();
        for (Vector dir : Vector.EIGHT_DIR_VECTORS) {
            String field = VectorUtils.add(square, dir);
            if (position.isValid(field) && (!position.fieldOccupied(square) || enemyVegetarianOnField(square))) {
                moves.add(new Move(square, field));
            }
        }
        return moves.toArray(new Move[moves.size()]);
    }
    
    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_penguin_dark : Globals.ts_female_penguin_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_penguin_dark : Globals.ts_male_penguin_light);
    }

}
