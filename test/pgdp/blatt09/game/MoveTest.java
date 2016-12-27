package pgdp.blatt09.game;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Mathias
 */
public class MoveTest {
    @Test
    public void testConstructor() {
        Move m = new Move("a2b3");
        Assert.assertEquals("a2", m.getFrom());
        Assert.assertEquals("b3", m.getTo());
    }
}
