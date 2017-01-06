package pgdp.blatt09.game;

/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

    protected int withoutFood = -1;
    
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
        if(!position.fieldOccupied(square)) return false;
        Animal a = position.getAnimal(square);
        return (a.female != female) && !(a instanceof Predator);
    }
    
    public int getWithoutFood() {
        return withoutFood;
    }
    
    public void resetWithoutFood() {}
    
    @Override
    public void sunset() {
        withoutFood--;
        if(withoutFood < 0) position.deleteAnimal(this);
    }
}
