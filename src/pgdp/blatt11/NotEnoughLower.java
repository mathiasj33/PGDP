package pgdp.blatt11;

public class NotEnoughLower extends NotEnoughLetter {

    public NotEnoughLower(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return StringFactory.shouldIsError(should, is, "lower-case letters");
    }
}
