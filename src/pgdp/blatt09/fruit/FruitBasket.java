package pgdp;

import java.util.LinkedList;

public class FruitBasket {

    private LinkedList<Fruit> fruits;

    public FruitBasket() {
        this.fruits = new LinkedList<>();
    }

    public void addFruit(Fruit f) {
        fruits.add(f);
    }

    public LinkedList<Apple> getApples() {
        LinkedList<Apple> result = new LinkedList<>();
        fruits.stream().filter(f -> f.isApple()).forEach(f -> result.add((Apple) f));
        return result;
    }

    public LinkedList<Fruit> getEqualOrLongerShelfLife(int n) {
        LinkedList<Fruit> result = new LinkedList<>();
        fruits.stream().filter(f -> f.shelfLife() >= n).forEach(result::add);
        return result;
    }
}
