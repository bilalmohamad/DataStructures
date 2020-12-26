package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Tests the TreeSet class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class TreeSetTest {

	/** Sample key used for testing */
	public static final String NAME_1 = "Bill Nye";
	/** Sample key used for testing */
	public static final String NAME_2 = "Bobby";
	/** Sample key used for testing */
	public static final String NAME_3 = "Billy";
	/** Sample key used for testing */
	public static final String NAME_4 = "Comp Sci";
	
	/** Sample key used for testing */
	public static final String NAME_5 = "Salami";
	/** Sample key used for testing */
	public static final String NAME_6 = "Marla";
	/** Sample key used for testing */
	public static final String NAME_7 = "John Cena";
	/** Sample key used for testing */
	public static final String NAME_8 = "Frog";
	
	
	/**
	 * Tests all the methods in the TreeSet class
	 */
	@Test
	public void testTreeSet() {
		
		Set<String> s1 = new TreeSet<String>();
		Set<String> s2 = new TreeSet<String>();
		assertEquals(true, s1.isEmpty());
		assertEquals(0, s1.size());
		
		
		//Tests the adding methods
		s1.add(NAME_1);
		s1.add(NAME_2);
		s1.add(NAME_3);
		s1.add(NAME_4);
		assertEquals(false, s1.isEmpty());
		assertEquals(4, s1.size());
		
		s2.add(NAME_5);
		s2.add(NAME_6);
		s2.add(NAME_7);
		s2.add(NAME_8);
		assertEquals(false, s2.isEmpty());
		assertEquals(4, s2.size());
		
		s1.addAll(s2);
		assertEquals(8, s1.size());
		
		
		//Tests the contains method
		assertEquals(true, s2.contains(NAME_5));
		assertEquals(false, s2.contains(NAME_1));
		
		
		//Tests the removing methods
		assertEquals(NAME_5, s1.remove(NAME_5));
		assertEquals(NAME_6, s1.remove(NAME_6));
		assertEquals(6, s1.size());
		
		s1.removeAll(s2);
		assertEquals(4, s1.size());
		
		
		//Tests the retainAll method
		s1.addAll(s2);
		assertEquals(8, s1.size());
		
		s1.remove(NAME_5);
		s1.remove(NAME_6);
		assertEquals(6, s1.size());
		
		s1.retainAll(s2);
		assertEquals(2, s1.size());
		assertEquals(true, s1.contains(NAME_7));
		assertEquals(true, s1.contains(NAME_8));
	}
}
