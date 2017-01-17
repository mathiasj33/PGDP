package pgdp.blatt08;

import org.junit.Assert;
import org.junit.Test;

public class SymmetricStackTest {

    @Test
    public void testIsEmpty() {
        SymmetricStack stack = new SymmetricStack();
        Assert.assertTrue(stack.isEmpty());
        stack.append(1);
        Assert.assertFalse(stack.isEmpty());
        stack.removeLast();
        Assert.assertTrue(stack.isEmpty());
        stack.prepend(2);
        Assert.assertFalse(stack.isEmpty());
        stack.removeFirst();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsFull() {
        SymmetricStack stack = new SymmetricStack();
        stack.append(1);
        stack.append(2);
        stack.append(3);
        stack.append(4);
        stack.append(5);
        stack.append(6);
        Assert.assertTrue(stack.isFull());

        stack = new SymmetricStack();
        stack.append(1);
        stack.append(2);
        stack.append(3);
        stack.prepend(-1);
        stack.prepend(-2);
        stack.prepend(-3);
        Assert.assertTrue(stack.isFull());

        stack = new SymmetricStack();
        stack.prepend(1);
        stack.prepend(2);
        stack.prepend(3);
        stack.prepend(4);
        stack.prepend(5);
        stack.prepend(6);
        Assert.assertTrue(stack.isFull());
    }

    @Test
    public void testGetNumberOfElements() {
        SymmetricStack stack = new SymmetricStack();
        stack.append(5);
        stack.append(2);
        stack.append(1);
        Assert.assertEquals(3, stack.getNumberOfElements());
        stack.append(1);
        Assert.assertEquals(4, stack.getNumberOfElements());
        stack.removeLast();
        stack.removeLast();
        stack.prepend(0);
        Assert.assertEquals(3, stack.getNumberOfElements());
        stack.prepend(0);
        stack.prepend(0);
        Assert.assertEquals(5, stack.getNumberOfElements());

        stack = new SymmetricStack();
        Assert.assertEquals(0, stack.getNumberOfElements());
        stack.append(2);
        Assert.assertEquals(1, stack.getNumberOfElements());
        stack.removeLast();
        Assert.assertEquals(0, stack.getNumberOfElements());
        stack.prepend(-1);
        stack.removeFirst();
        Assert.assertEquals(0, stack.getNumberOfElements());
    }

    @Test
    public void testRemove() {
        SymmetricStack stack = new SymmetricStack();
        stack.append(5);
        stack.append(2);
        stack.removeLast();
        Assert.assertEquals(1, stack.getNumberOfElements());
        stack.append(2);
        stack.append(2);
        stack.append(2);
        stack.append(2);
        stack.removeLast();
        Assert.assertEquals(4, stack.getNumberOfElements());
        stack.removeLast();
        Assert.assertEquals(3, stack.getNumberOfElements());

        stack = new SymmetricStack();
        stack.prepend(0);
        stack.prepend(0);
        stack.removeFirst();
        Assert.assertEquals(1, stack.getNumberOfElements());
        stack.prepend(4);
        stack.prepend(4);
        stack.prepend(4);
        stack.prepend(4);
        stack.removeFirst();
        Assert.assertEquals(4, stack.getNumberOfElements());

        stack = new SymmetricStack(8);
        stack.setData(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        stack.setFirst(3);
        stack.setLast(2);
        for (int i = 0; i < 8; i++) {
            System.out.println(stack);
            stack.removeLast();
        }
    }

//	@Test
//	public void testIncrease() {
//		SymmetricStack stack = new SymmetricStack(5);
//		stack.append(2);
//		stack.append(9);
//		stack.append(0);
//		stack.append(3);
//		stack.append(5);
//		System.out.println(stack);
//		stack.increase();
//		System.out.println(stack);
//	}
//	
//	@Test
//	public void testAppend() {
//		SymmetricStack stack = new SymmetricStack(5);
//		stack.append(2);
//		stack.append(9);
//		stack.append(0);
//		stack.append(3);
//		stack.append(5);
//		System.out.println(stack);
//		stack.prepend(-1);
//		System.out.println(stack);
//	}
//	@Test
//	public void testDecrease() {
//		SymmetricStack stack = new SymmetricStack(8);
//		stack.append(3);
//		stack.append(5);
//		System.out.println(stack);
//		stack.decrease();
//		System.out.println(stack);
//		
//		stack = new SymmetricStack(8);
//		stack.setData(new int[]{5,1,2,3,4,5,6,3});
//		stack.setFirst(7);
//		stack.setLast(0);
//		System.out.println(stack);
//		stack.decrease();
//		System.out.println(stack);
//	}
}
