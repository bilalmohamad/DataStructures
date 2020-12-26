package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

/**
 * Tests the ArrayBasedList class
 * 
 * @author Bilal Mohamad
 *
 */
public class ArrayBasedListTest {

	/** Example array used for testing */
	public static final int[] NUMBERS = {10, 20, 30, 50, 100};
	
	
	/**
	 * Tests the add method of the ArrayBasedList class
	 */
	@Test
	public void testAdd() {
		
		ArrayBasedList<Integer> abl = new ArrayBasedList<Integer>(NUMBERS.length);
		
		assertEquals(true, abl.isEmpty());
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abl.add(i, NUMBERS[i]);
		}
		
		assertEquals(5, abl.size());
		
		for (int i = 0; i < NUMBERS.length; i++) {
//			System.out.println(abl.get(i));
			assertEquals(NUMBERS[i], (int)abl.get(i));
		}
		
		abl.addFirst(5);
		assertEquals(6, abl.size());
		assertEquals(5, (int)abl.first());
		
		abl.addLast(200);
		assertEquals(7, abl.size());
		assertEquals(200, (int)abl.last());
		
		assertEquals(false, abl.isEmpty());
		
		try {
			abl.add(-1, 9999);
			fail();
		}
		catch (Exception e) {
			assertNotNull(e.getMessage());
		}
		
	}
	
	
	/**
	 * Extra testing in hopes of finding further errors
	 */
	@Test
	public void testExtraTests() {
		
		ArrayBasedList<String> abl = new ArrayBasedList<String>();
		
		abl.add(0, "A");
		abl.add(1, "B");
		abl.add(2, "C");
		
		assertEquals(3, abl.size());
		
		Iterator<String> ei = abl.iterator();
		
		assertEquals(true, ei.hasNext());
		
		try {
			ei.remove();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}
		
		assertEquals("A", ei.next());
		
		assertEquals(3, abl.size());
		
		ei.remove();
		
		assertEquals(2, abl.size());
		assertEquals("B", abl.get(0));
		assertEquals("C", abl.get(1));
		
		try {
			ei.remove();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}
		
		assertEquals(true, ei.hasNext());
		assertEquals("B", ei.next());
		
		ei.remove();
		
		assertEquals(1, abl.size());
		assertEquals("C", abl.get(0));
		
		try {
			ei.remove();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}
		
		assertEquals(true, ei.hasNext());
		assertEquals("C", ei.next());
		
		ei.remove();
		
		assertEquals(0, abl.size());
		
		try {
			ei.remove();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}
		
		assertEquals(false, ei.hasNext());
		
		/*try {
			ei.next();
			fail();
		}
		catch (Exception e) {
			assertNull(e.getMessage());
		}*/
		
		
	}
	
	
	/**
	 * Tests the remove method of the ArrayBasedList class
	 */
	@Test
	public void testRemove() {
		
		ArrayBasedList<Integer> abl = new ArrayBasedList<Integer>(NUMBERS.length);
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abl.add(i, NUMBERS[i]);
		}
		
		assertEquals(10, (int)abl.remove(0));
		assertEquals(4, abl.size());
		
		/*assertEquals(20, (int)abl.remove(0));
		assertEquals(3, abl.size());
		
		assertEquals(30, (int)abl.remove(0));
		assertEquals(2, abl.size());
		
		assertEquals(50, (int)abl.remove(0));
		assertEquals(1, abl.size());
		
		assertEquals(100, (int)abl.remove(0));
		assertEquals(0, abl.size());*/
		
		for (int i = 0; i < NUMBERS.length - 1; i++) {
//			System.out.println(abl.get(i));
			assertEquals(NUMBERS[i + 1], (int)abl.get(i));
		}
		
		assertEquals(20, (int) abl.removeFirst());
		assertEquals(100, (int) abl.removeLast());
	}
	
	
	/**
	 * Tests the set method of the ArrayBasedList class
	 */
	@Test
	public void testSet() {
		
		ArrayBasedList<Integer> abl = new ArrayBasedList<Integer>(NUMBERS.length);
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abl.add(i, NUMBERS[i]);
		}
		
		assertEquals(100, (int)abl.set(4, 200));
		assertEquals(200, (int)abl.get(4));
		assertEquals(5, abl.size());
		
		for (int i = 0; i < NUMBERS.length - 1; i++) {
//			System.out.println(abl.get(i));
			assertEquals(NUMBERS[i], (int)abl.get(i));
		}
	}
	
	
	/**
	 * Tests the iterator method of the ArrayBasedList class
	 * as well as the ElementIterator inner class methods
	 */
	@Test
	public void testIterator() {
		
		ArrayBasedList<Integer> ablEmpty = new ArrayBasedList<Integer>();
		
		Iterator<Integer> eiEmpty = ablEmpty.iterator();
		
		assertFalse(eiEmpty.hasNext());
		//assertNotNull(eiEmpty.next());
		
		
		ArrayBasedList<Integer> abl = new ArrayBasedList<Integer>(NUMBERS.length);
		
		Iterator<Integer> ei = abl.iterator();
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abl.add(i, NUMBERS[i]);
		}
		
		assertEquals(true, ei.hasNext());
		assertEquals(10, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(20, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(30, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(50, (int)ei.next());
		
		assertEquals(true, ei.hasNext());
		assertEquals(100, (int)ei.next());
		
//		assertEquals(false, ei.hasNext());
		
		
		ei = abl.iterator();
		
		ei.next();
		ei.remove();
		
		//TODO CHECK IF THIS WAS DONE RIGHT
		assertEquals(20, (int)ei.next());
	}
	
}
