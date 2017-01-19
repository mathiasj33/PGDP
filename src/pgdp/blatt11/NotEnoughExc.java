
package pgdp.blatt11;

public class NotEnoughExc extends Exception {
    protected final int should, is;

    public NotEnoughExc(int should, int is) {
        this.should = should;
        this.is = is;
    }
}
