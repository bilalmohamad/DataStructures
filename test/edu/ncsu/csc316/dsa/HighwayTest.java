package edu.ncsu.csc316.dsa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the Highway class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class HighwayTest {

	/**
	 * Tests the methods of the Highway class
	 */
	@Test
	public void testHighway() {
		
		Highway h = new Highway("Bill Nye", 5);
		
		assertEquals("Bill Nye", h.getName());
		assertEquals(5, h.getLength());
		assertEquals(false, h.isFound());
	}
}
