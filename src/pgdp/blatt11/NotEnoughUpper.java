
package pgdp.blatt11;

public class NotEnoughUpper extends NotEnoughLetter {

    public NotEnoughUpper(int should, int is) {
        super(should, is);
    }
    
    @Override
    public String toString() {
        return StringFactory.shouldIsError(should, is, "upper-case letters");
    }
}
