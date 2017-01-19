
package pgdp.blatt11;

public class NotLongEnoughExc extends Exception {
    private int should, is;

    public NotLongEnoughExc(int should, int is) {
        this.should = should;
        this.is = is;
    }
    
    @Override
    public String toString() {
        return "Error: The password is not long enough. It has " + is + " characters, while at least " + should + " characters are required.";
    }
}
