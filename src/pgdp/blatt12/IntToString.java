
package pgdp.blatt12;

public class IntToString implements Fun<Integer, String> {

    @Override
    public String apply(Integer x) {
        return "" + x;
    }

}
