
package pgdp.blatt11;

public class NotEnoughUpper extends NotEnoughLetter {

    public NotEnoughUpper(int should, int is) {
        super(should, is);
    }
    
    @Override
    public String toString() {
        return shouldIsError("upper-case letters");
    }
}
