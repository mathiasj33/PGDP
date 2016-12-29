package pgdp.blatt09.game;

/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Predator(boolean female) {
        super(female);
    }

    public Predator(boolean female, String square, Position position) {
        super(female, square, position);
    }
    
    protected boolean enemyVegetarianOnField(String square) {
        Animal a = position.getAnimal(square);
        return (a.female != female) && !(a instanceof Predator);
    }
}
