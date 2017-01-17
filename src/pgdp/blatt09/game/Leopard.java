package pgdp.blatt09.game;

public class Leopard extends Predator {
    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Leopard(boolean female) {
        super(female);
        withoutFood = 5;
        initialWithoutFood = 5;
    }

    public Leopard(boolean female, String square, Position position) {
        super(female, square, position);
        withoutFood = 5;
        initialWithoutFood = 5;
    }
    
    @Override
    public Move[] possibleMoves() {
        List<Move> moves = new List<>();
        for(Vector dir : Vector.EIGHT_DIR_VECTORS) {
            Vector pos = VectorUtils.squareToVector(square);
            while(true) {
                pos = pos.add(dir);
                String posSquare = VectorUtils.vectorToSquare(pos);
                if(!position.isValid(posSquare) || position.fieldOccupied(posSquare) && !enemyVegetarianOnField(posSquare)) {
                    break;
                }
                moves.add(new Move(this.square, posSquare));
                if(enemyVegetarianOnField(posSquare)) {
                    break;
                }
            }
        }
        return moves.toArray(new Move[moves.size()]);
    }
    
    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_leopard_dark : Globals.ts_female_leopard_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_leopard_dark : Globals.ts_male_leopard_light);
    }

}
