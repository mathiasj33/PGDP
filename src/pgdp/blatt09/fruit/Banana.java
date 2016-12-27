package pgdp;

public class Banana extends Fruit {

    @Override
    public int shelfLife() {
        return 7;
    }

    @Override
    public boolean isBanana() {
        return true;
    }
}
