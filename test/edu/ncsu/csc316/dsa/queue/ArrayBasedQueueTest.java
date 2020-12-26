package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the ArrayBasedQueue class
 * 
 * @author Bilal Mohamad
 *
 */
public class ArrayBasedQueueTest {

	/** Example array used for testing */
	public static final int[] NUMBERS = {10, 20, 30, 50, 100};
	
	
	/**
	 * Tests the enqueue method
	 */
	@Test
	public void testEnqueue() {
		
		ArrayBasedQueue<Integer> abq = new ArrayBasedQueue<Integer>();
		assertEquals(0, abq.size());
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abq.enqueue(NUMBERS[i]);
			assertEquals(i + 1, abq.size());
		}
	}
	
	
	/**
	 * Tests the dequeue method
	 */
	@Test
	public void testDequeue() {
		
		ArrayBasedQueue<Integer> abq = new ArrayBasedQueue<Integer>();
		assertEquals(0, abq.size());
		
		try {
			abq.dequeue();
			fail();
		}
		catch (NoSuchElementException e) {
			assertEquals(null, e.getMessage());
		}
		
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abq.enqueue(NUMBERS[i]);
			assertEquals(i + 1, abq.size());
		}
		
		for (int i = 0; i < NUMBERS.length; i++) {
			assertEquals(NUMBERS[i], (int) abq.front());
			assertEquals(NUMBERS[i], (int) abq.dequeue());
		}
	}
	
	
	/**
	 * Tests the front method
	 */
	@Test
	public void testFront() {
		
		ArrayBasedQueue<Integer> abq = new ArrayBasedQueue<Integer>();
		assertEquals(0, abq.size());
		
		try {
			abq.front();
			fail();
		}
		catch (NoSuchElementException e) {
			assertEquals(null, e.getMessage());
		}
		
		
		for (int i = 0; i < NUMBERS.length; i++) {
			abq.enqueue(NUMBERS[i]);
			assertEquals(NUMBERS[0], (int) abq.front());
		}
	}
}
