package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Tests the LinearProbingHashMap class
 * 
 * @author Bilal Mohamad (bmohama)
 *
 */
public class LinearProbingHashMapTest {

	/** Sample key used for testing */
	public static final String NAME_1 = "Bill Nye";
	/** Sample key used for testing */
	public static final String NAME_2 = "Billy";
	/** Sample key used for testing */
	public static final String NAME_3 = "Bobby";
	/** Sample key used for testing */
	public static final String NAME_4 = "Comp Sci";
	/** Sample key used for testing */
	public static final String NAME_5 = "Jameek";
	/** Sample key used for testing */
	public static final String NAME_6 = "Hit the";
	/** Sample key used for testing */
	public static final String NAME_7 = "Quan";
	
	/** Sample value used for testing */
	public static final String PHONE_1 = "111-111-1111";
	/** Sample value used for testing */
	public static final String PHONE_2 = "222-222-2222";
	/** Sample value used for testing */
	public static final String PHONE_3 = "333-333-3333";
	/** Sample value used for testing */
	public static final String PHONE_4 = "444-444-4444";
	/** Sample value used for testing */
	public static final String PHONE_5 = "555-555-5555";
	/** Sample value used for testing */
	public static final String PHONE_6 = "666-666-6666";
	/** Sample value used for testing */
	public static final String PHONE_7 = "777-777-7777";
	
	
	
	/**
	 * Tests the methods from the LinearProbingHashMap class
	 */
	@Test
	public void testLinearProbingHashMap() {
		
		AbstractHashMap<String, String> lphm = new LinearProbingHashMap<String, String>();
		assertEquals(0, lphm.size());
		assertEquals(true, lphm.isEmpty());
		
		
		
		//Tests the null cases for the bucketGet and bucketRemove methods
		assertEquals(null, lphm.bucketGet(1, "dummy"));
		assertEquals(null, lphm.bucketRemove(1, "dummy"));
		
		
		
		//Tests the bucketPut method
		assertEquals(null, lphm.bucketPut(0, NAME_1, PHONE_1));
		assertEquals(null, lphm.bucketPut(0, NAME_2, PHONE_2));
		assertEquals(null, lphm.bucketPut(0, NAME_3, PHONE_3));
		
		assertEquals(null, lphm.bucketPut(1, NAME_4, PHONE_4));
		
		assertEquals(null, lphm.bucketPut(2, NAME_5, PHONE_5));
		assertEquals(null, lphm.bucketPut(2, NAME_6, PHONE_6));
		assertEquals(PHONE_6, lphm.bucketPut(2, NAME_6, PHONE_7));
		
		assertEquals(6, lphm.size());
		
		
		
		//Tests the bucketRemove method
		assertEquals(PHONE_7, lphm.bucketRemove(2, NAME_6));
		assertEquals(5, lphm.size());
		
		
		
		//Tests the bucketGet method
		assertEquals(PHONE_1, lphm.bucketGet(0, NAME_1));
		assertEquals(PHONE_2, lphm.bucketGet(0, NAME_2));
		assertEquals(PHONE_3, lphm.bucketGet(0, NAME_3));
		assertEquals(PHONE_4, lphm.bucketGet(1, NAME_4));
		assertEquals(PHONE_5, lphm.bucketGet(2, NAME_5));
		
		
		
		//Tests the entrySet method
		assertNotNull(lphm.entrySet());
	}
	
	
	
	/**
	 * Tests the methods from the AbstractHashMap parent class
	 */
	@Test
	public void testAbstractHashMap() {
		
		AbstractHashMap<String, String> lphm = new LinearProbingHashMap<String, String>(2);
		assertEquals(0, lphm.size());
		assertEquals(true, lphm.isEmpty());
		
		
		
		//Tests the put method
		assertEquals(null, lphm.put(NAME_1, PHONE_1));
		assertEquals(null, lphm.put(NAME_2, PHONE_2));
		assertEquals(null, lphm.put(NAME_3, PHONE_3));
		assertEquals(null, lphm.put(NAME_4, PHONE_4));
		
		assertEquals(4, lphm.size());
		
		
		
		//Tests the remove method
		assertEquals(PHONE_4, lphm.remove(NAME_4));
		assertEquals(3, lphm.size());
		
		
		
		//Tests the get method
		assertEquals(PHONE_1, lphm.get(NAME_1));
		assertEquals(PHONE_2, lphm.get(NAME_2));
		assertEquals(PHONE_3, lphm.get(NAME_3));
	}
}
