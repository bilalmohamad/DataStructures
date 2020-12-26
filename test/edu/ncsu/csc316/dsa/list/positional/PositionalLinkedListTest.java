package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the PositionalLinkedList class
 * 
 * @author Bilal Mohamad
 *
 */
public class PositionalLinkedListTest {

	/** Example array used for testing */
	public static final int[] NUMBERS = {10, 20, 30, 50, 100};
	
	
	/**
	 * Tests the addFirst, addLast, addBefore, and addAfter methods
	 */
	@Test
	public void testAdds() {
		
		PositionalLinkedList<Integer> pll = new PositionalLinkedList<Integer>();
		
		assertEquals(true, pll.isEmpty());
		
		pll.addFirst(10);
		assertEquals(1, pll.size());
		assertEquals(10, (int) pll.first().getElement());
		
		pll.addAfter(pll.first(), 20);
		assertEquals(2, pll.size());
		assertEquals(20, (int) pll.after(pll.first()).getElement());
		
		pll.addBefore(pll.first(), 15);
		assertEquals(3, pll.size());
		assertEquals(15, (int) pll.first().getElement());
		
		pll.addLast(30);
		assertEquals(4, pll.size());
		assertNotNull(pll.last());
		
		/*try {
			pll.before(pll.last());
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}*/
//		assertEquals(null, pll.before(pll.first()));
		
		assertEquals(false, pll.isEmpty());
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		
		PositionalLinkedList<Integer> pll = new PositionalLinkedList<Integer>();
		
		pll.addFirst(100);
		pll.addFirst(50);
		pll.addFirst(30);
		pll.addFirst(20);
		pll.addFirst(10);
		
		assertEquals(5, pll.size());
		
		assertEquals(10, (int)pll.remove(pll.first()));
		assertEquals(4, pll.size());
		
		assertEquals(20, (int)pll.remove(pll.first()));
		assertEquals(3, pll.size());
	}
	
	
	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		
		PositionalLinkedList<Integer> pll = new PositionalLinkedList<Integer>();
		
		pll.addFirst(100);
		pll.addFirst(50);
		pll.addFirst(30);
		pll.addFirst(20);
		pll.addFirst(10);
		
		assertEquals(5, pll.size());
		
		assertEquals(10, (int)pll.set(pll.first(), 15));
		assertEquals(15, (int)pll.first().getElement());
	}
		
	
	/**
	 * Tests the iterator method in the SinglyLinkedList class
	 * as well as the methods of the ElementIterator inner class
	 */
	@Test
	public void testIterator() {
		
		PositionalLinkedList<Integer> pll = new PositionalLinkedList<Integer>();
		
		Iterator<Integer> eiEmpty = pll.iterator();
		
		/*try {
			eiEmpty.next();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}*/
		
		try {
			eiEmpty.remove();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}
		
		for (int i = 0; i < NUMBERS.length; i++) {
			pll.addFirst(NUMBERS[i]);
		}
		
		assertEquals(5, pll.size());
		
		Iterator<Integer> ei = pll.iterator();
		
		assertEquals(true, ei.hasNext());
		assertEquals(100, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(50, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(30, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(20, (int)ei.next());
		
		ei = pll.iterator();
		
		ei.next();
		ei.remove();
		
		//TODO CHECK IF THIS WAS DONE RIGHT
//		assertEquals(50, (int)ei.next());
		
	}
	
	
	/**
	 * Tests the positions method
	 */
	@Test
	public void testPositions() {
		
		PositionalLinkedList<Integer> pll = new PositionalLinkedList<Integer>();
		
		for (int i = 0; i < NUMBERS.length; i++) {
			pll.addFirst(NUMBERS[i]);
		}
		
		Iterable<Position<Integer>> iter = pll.positions();
		
		assertEquals(true, iter.iterator().hasNext());
	}
}
