package pgdp.blatt09.game;

/**
 * Klasse der Vegetarier.
 */
public class Vegetarian extends Animal {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Vegetarian(boolean female) {
        super(female);
    }

    public Vegetarian(boolean female, String square, Position position) {
        super(female, square, position);
    }
    
    @Override
    public void sunset() {
        
    }
}
