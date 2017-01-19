
package pgdp.blatt11;

public class NotEnoughNumber extends NotEnoughExc {

    public NotEnoughNumber(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return StringFactory.shouldIsError(should, is, "numbers");
    }
}
