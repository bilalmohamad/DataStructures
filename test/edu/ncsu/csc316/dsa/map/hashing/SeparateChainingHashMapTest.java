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
public class SeparateChainingHashMapTest {

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
	 * Tests the methods from the SeparateChainingHashMap class
	 */
	@Test
	public void testSeparateChainingHashMap() {
		
		AbstractHashMap<String, String> schm = new SeparateChainingHashMap<String, String>();
		assertEquals(0, schm.size());
		assertEquals(true, schm.isEmpty());
		
		
		
		//Tests the null cases for the bucketGet and bucketRemove methods
		assertEquals(null, schm.bucketGet(1, "dummy"));
		assertEquals(null, schm.bucketRemove(1, "dummy"));
		
		
		
		//Tests the bucketPut method
		assertEquals(null, schm.bucketPut(0, NAME_1, PHONE_1));
		assertEquals(null, schm.bucketPut(0, NAME_2, PHONE_2));
		assertEquals(null, schm.bucketPut(0, NAME_3, PHONE_3));
		
		assertEquals(null, schm.bucketPut(1, NAME_4, PHONE_4));
		
		assertEquals(null, schm.bucketPut(2, NAME_5, PHONE_5));
		assertEquals(null, schm.bucketPut(2, NAME_6, PHONE_6));
		assertEquals(PHONE_6, schm.bucketPut(2, NAME_6, PHONE_7));
		
		assertEquals(6, schm.size());
		
		
		
		//Tests the bucketRemove method
		assertEquals(PHONE_7, schm.bucketRemove(2, NAME_6));
		assertEquals(5, schm.size());
		
		
		
		//Tests the bucketGet method
		assertEquals(PHONE_1, schm.bucketGet(0, NAME_1));
		assertEquals(PHONE_2, schm.bucketGet(0, NAME_2));
		assertEquals(PHONE_3, schm.bucketGet(0, NAME_3));
		assertEquals(PHONE_4, schm.bucketGet(1, NAME_4));
		assertEquals(PHONE_5, schm.bucketGet(2, NAME_5));
		
		
		
		//Tests the entrySet method
		assertNotNull(schm.entrySet());
	}
	
	
	/**
	 * Tests the methods from the AbstractHashMap parent class
	 */
	@Test
	public void testAbstractHashMap() {
		
		AbstractHashMap<String, String> schm = new SeparateChainingHashMap<String, String>(2);
		assertEquals(0, schm.size());
		assertEquals(true, schm.isEmpty());
		
		
		
		//Tests the put method
		assertEquals(null, schm.put(NAME_1, PHONE_1));
		assertEquals(null, schm.put(NAME_2, PHONE_2));
		assertEquals(null, schm.put(NAME_3, PHONE_3));
		assertEquals(null, schm.put(NAME_4, PHONE_4));
		
		assertEquals(4, schm.size());
		
		
		
		//Tests the remove method
		assertEquals(PHONE_4, schm.remove(NAME_4));
		assertEquals(3, schm.size());
		
		
		
		//Tests the get method
		assertEquals(PHONE_1, schm.get(NAME_1));
		assertEquals(PHONE_2, schm.get(NAME_2));
		assertEquals(PHONE_3, schm.get(NAME_3));
	}
}
