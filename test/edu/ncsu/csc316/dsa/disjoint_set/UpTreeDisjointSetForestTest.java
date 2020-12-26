package edu.ncsu.csc316.dsa.disjoint_set;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the UpTreeDisjointSetForest class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class UpTreeDisjointSetForestTest {

	/** Sample key used for testing */
	public static final String NAME_1 = "Bill Nye";
	/** Sample key used for testing */
	public static final String NAME_2 = "Bobby";
	/** Sample key used for testing */
	public static final String NAME_3 = "Billy";
	/** Sample key used for testing */
	public static final String NAME_4 = "Comp Sci";
	
	
	/**
	 * Tests all the methods in the UpTreeDisjointSetForest class
	 */
	@Test
	public void testUpTreeDisjointSetForest() {
		
		DisjointSet<String> ds = new UpTreeDisjointSetForest<String>();
		
		Position<String> p1 = ds.makeSet(NAME_1);
		Position<String> p2 = ds.makeSet(NAME_2);
		Position<String> p3 = ds.makeSet(NAME_3);
		Position<String> p4 = ds.makeSet(NAME_4);
		
		assertEquals(p1, ds.find(NAME_1));
		assertEquals(p2, ds.find(NAME_2));
		assertEquals(p3, ds.find(NAME_3));
		assertEquals(p4, ds.find(NAME_4));
		
		
		ds.union(p1, p2);
		ds.union(p1, p3);
		ds.union(p1, p4);
		
		
		assertEquals(NAME_1, p1.getElement());
		assertEquals(NAME_2, p2.getElement());
		assertEquals(NAME_3, p3.getElement());
		assertEquals(NAME_4, p4.getElement());
	}
}
