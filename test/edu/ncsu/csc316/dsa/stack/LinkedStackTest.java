package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests the LinkedStack class
 * 
 * @author Bilal Mohamad
 *
 */
public class LinkedStackTest {

	/** Example array used for testing */
	public static final int[] NUMBERS = {10, 20, 30, 50, 100};
	
	
	/**
	 * Tests the push method
	 */
	@Test
	public void testPush() {
		
		LinkedStack<Integer> ls = new LinkedStack<Integer>();
		assertEquals(0, ls.size());
		
		ls.push(NUMBERS[0]);
		assertEquals(1, ls.size());
		
		ls.push(NUMBERS[1]);
		assertEquals(2, ls.size());
		
		ls.push(NUMBERS[2]);
		assertEquals(3, ls.size());
		
		ls.push(NUMBERS[3]);
		assertEquals(4, ls.size());
		
		ls.push(NUMBERS[4]);
		assertEquals(5, ls.size());
	}
	
	
	/**
	 * Tests the pop method
	 */
	@Test
	public void testPop() {
		
		LinkedStack<Integer> ls = new LinkedStack<Integer>();
		assertEquals(0, ls.size());
		
		try {
			ls.pop();
			fail();
		}
		catch (EmptyStackException e) {
			assertNull(e.getMessage());
		}
		
		
		for (int i = 0; i < NUMBERS.length; i++) {
			ls.push(NUMBERS[i]);
		}
		
		for (int i = NUMBERS.length - 1; i >= 0; i--) {
			assertEquals(NUMBERS[i], (int)ls.pop());
		}
	}
	
	
	/**
	 * Tests the top method
	 */
	@Test
	public void testTop() {
		
		LinkedStack<Integer> ls = new LinkedStack<Integer>();
		assertEquals(0, ls.size());
		
		try {
			ls.top();
			fail();
		}
		catch (EmptyStackException e) {
			assertNull(e.getMessage());
		}
		
		
		for (int i = 0; i < NUMBERS.length; i++) {
			ls.push(NUMBERS[i]);
			assertEquals(NUMBERS[i], (int) ls.top());
		}
	}
}
