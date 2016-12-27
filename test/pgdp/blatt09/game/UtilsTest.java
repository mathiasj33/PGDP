package pgdp.blatt09.game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mathias
 */
public class UtilsTest {

    @Test
    public void testPosToVector() {
        assertEquals(new Vector(4, 2), VectorUtils.squareToVector("e3"));
        assertEquals(new Vector(2, 0), VectorUtils.squareToVector("c1"));
    }
    
    @Test
    public void testVectorToPos() {
        assertEquals("e3", VectorUtils.vectorToSquare(new Vector(4,2)));
    }
    
    @Test
    public void testAdd() {
        assertEquals("f3", VectorUtils.add("e3", new Vector(1,0)));
        assertEquals("d3", VectorUtils.add("e3", new Vector(-1,0)));
        
        assertEquals("e4", VectorUtils.add("e3", new Vector(0,1)));
        assertEquals("e2", VectorUtils.add("e3", new Vector(0,-1)));
        
        assertEquals("d4", VectorUtils.add("e3", new Vector(-1,1)));
    }
}
