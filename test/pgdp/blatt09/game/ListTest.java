package pgdp.blatt09.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mathias
 */
public class ListTest {
    
    private List<String> list;
    
    @Before
    public void setup() {
        list = new List<>(5);
        list.addAll("a", "b", "c");
    }

    @Test
    public void testGet() {
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
        try {
            list.get(3);
            fail("Should have thrown IndexOutOfBoundsException");
        }
        catch(IndexOutOfBoundsException oobe) {
        }
    }

    @Test
    public void testGrowAndShrink() {
        list.add("d");
        list.add("e");
        list.add("f");
        assertEquals(10, list.getArrayLength());
        list.remove("f");
        assertEquals(10, list.getArrayLength());
        list.remove("e");
        list.remove("d");
        assertEquals(5, list.getArrayLength());
        list.add("x");
        assertEquals(5, list.getArrayLength());
    }

    @Test
    public void testAddAndRemove() {
        list.remove("a");
        assertEquals("b", list.get(0));
        assertEquals("c", list.get(1));
        list.add("d");
        assertEquals("d", list.get(2));
        list.remove("c");
        assertEquals("d", list.get(1));
    }

    @Test
    public void testContains() {
        assertTrue(list.contains("b"));
        assertFalse(list.contains("f"));
        list.add("x");
        assertTrue(list.contains("x"));
        list.remove("b");
        assertFalse(list.contains("b"));
    }

    @Test
    public void testIndexOf() {
        assertEquals(0, list.indexOf("a"));
        assertEquals(2, list.indexOf("c"));
        list.add("d");
        assertEquals(3, list.indexOf("d"));
        list.remove("b");
        assertEquals(2, list.indexOf("d"));
        assertEquals(-1, list.indexOf("b"));
    }

    @Test
    public void testSize() {
        assertEquals(3, list.size());
        list.add("x");
        assertEquals(4, list.size());
        list.addAll("y", "z");
        assertEquals(6, list.size());
        list.remove("z");
        assertEquals(5, list.size());
        list.remove("y");
        list.remove("x");
        list.remove("c");
        assertEquals(2, list.size());
    }
    
    @Test
    public void testToString() {
        assertEquals("[a,b,c]", list.toString());
        assertEquals("[]", new List<>().toString());
    }
}
