
package pgdp.blatt11;

public class NotEnoughExc extends Exception {
    protected final int should, is;

    public NotEnoughExc(int should, int is) {
        this.should = should;
        this.is = is;
    }
    
    protected String shouldIsError(String subject) {
        return "Error: The password does not contain enough " + subject + ". It has " + is + " " + subject + ", while at least " + should + " " + subject + " are required.";
    }
}
