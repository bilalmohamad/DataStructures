package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

/**
 * Tests the SinglyLinkedList class
 * 
 * @author Bilal Mohamad
 *
 */
public class SinglyLinkedListTest {

	/** Example array used for testing */
	public static final int[] NUMBERS = {10, 20, 30, 50, 100};
	
	
	/**
	 * Tests the add method in the SinglyLinkedList class
	 */
	@Test
	public void testAdd() {
		
		SinglyLinkedList<Integer> sli = new SinglyLinkedList<Integer>();
		assertEquals(0, sli.size());
		
		try {
			sli.last();
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		sli.add(0, NUMBERS[0]);
		sli.add(1, NUMBERS[2]);
		sli.add(1, NUMBERS[1]);
		sli.add(3, NUMBERS[4]);
		sli.add(3, NUMBERS[3]);
		
		for (int i = 0; i < NUMBERS.length; i++) {
//			System.out.println(sli.get(i));
			assertEquals(NUMBERS[i], (int)sli.get(i));
		}
		
		assertEquals(5, sli.size());
	}
	
	
	/**
	 * Tests the last method in the SinglyLinkedList class
	 */
	@Test
	public void testLast() {
		
		SinglyLinkedList<Integer> sli = new SinglyLinkedList<Integer>();
		assertEquals(0, sli.size());
		
		for (int i = 0; i < NUMBERS.length; i++) {
			sli.add(i, NUMBERS[i]);
			System.out.println(sli.last());
		}
		
		
		//TODO CHECK IF DONE WRONG
//		assertEquals(null, sli.last());
	}
	
	
	
	/**
	 * Tests the remove method in the SinglyLinkedList class
	 */
	@Test
	public void testRemove() {
		
		SinglyLinkedList<Integer> sli = new SinglyLinkedList<Integer>();
		assertEquals(0, sli.size());
		
		for (int i = 0; i < NUMBERS.length; i++) {
			sli.add(i, NUMBERS[i]);
		}
		
		sli.remove(0);
		assertEquals(4, sli.size());
		
		sli.remove(2);
		assertEquals(3, sli.size());
		
		assertEquals(NUMBERS[1], (int)sli.get(0));
		assertEquals(NUMBERS[2], (int)sli.get(1));
		assertEquals(NUMBERS[4], (int)sli.get(2));
		
		
		//Tests invalid index values
		try {
			sli.remove(-1);
			fail();
		}
		catch (Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			sli.remove(sli.size() + 1);
			fail();
		}
		catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}
	
	
	/**
	 * Tests the set method in the SinglyLinkedList class
	 */
	@Test
	public void testSet() {
		
		SinglyLinkedList<Integer> sli = new SinglyLinkedList<Integer>();
		assertEquals(0, sli.size());
		
		for (int i = 0; i < NUMBERS.length; i++) {
			sli.add(i, NUMBERS[i]);
		}
		
		sli.set(3, 40);
		
		assertEquals(NUMBERS[0], (int)sli.get(0));
		assertEquals(NUMBERS[1], (int)sli.get(1));
		assertEquals(NUMBERS[2], (int)sli.get(2));
		assertEquals(40, (int)sli.get(3));
		assertEquals(NUMBERS[4], (int)sli.get(4));
	}
	
	
	/**
	 * Tests the iterator method in the SinglyLinkedList class
	 * as well as the methods of the ElementIterator inner class
	 */
	@Test
	public void testIterator() {
		
		SinglyLinkedList<Integer> sli = new SinglyLinkedList<Integer>();
		
		for (int i = 0; i < NUMBERS.length; i++) {
			sli.add(i, NUMBERS[i]);
		}
		
		Iterator<Integer> ei = sli.iterator();
		
//		ei.remove();
		
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
		
		assertEquals(false, ei.hasNext());
		
		ei = sli.iterator();
		
		ei.next();
	}
	
	
	/**
	 * Tests the iterator method in the SinglyLinkedList class
	 * as well as the methods of the ElementIterator inner class
	 * but more in-depth on the remove method
	 */
	@Test
	public void testIteratorRemove() {
		
		SinglyLinkedList<Integer> sli = new SinglyLinkedList<Integer>();
		
		for (int i = 0; i < NUMBERS.length; i++) {
			sli.add(i, NUMBERS[i]);
		}
		
		Iterator<Integer> ei = sli.iterator();
		
		ei.next();
		ei.remove();
		
		ei.next();
		ei.remove();
		
		ei.next();
		ei.remove();
		
		ei.next();
		ei.remove();
		
		ei.next();
		ei.remove();
		
		assertEquals(false, ei.hasNext());
		
		ei = sli.iterator();
		
		ei.next();
		
	}
}
