package pgdp.blatt11;

public class NotEnoughSpecial extends NotEnoughExc {

    public NotEnoughSpecial(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return shouldIsError("special characters");
    }
}
