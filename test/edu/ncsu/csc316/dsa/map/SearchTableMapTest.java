package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Tests the SearchTableMap class
 * 
 * @author Bilal Mohamad
 *
 */
public class SearchTableMapTest {

	/** Sample key used for testing */
	public static final String NAME_1 = "Bill Nye";
	/** Sample key used for testing */
	public static final String NAME_2 = "Bobby";
	/** Sample key used for testing */
	public static final String NAME_3 = "Billy";
	/** Sample key used for testing */
	public static final String NAME_4 = "Comp Sci";
	
	/** Sample value used for testing */
	public static final String PHONE_1 = "555-555-5555";
	/** Sample value used for testing */
	public static final String PHONE_2 = "666-666-6666";
	/** Sample value used for testing */
	public static final String PHONE_3 = "777-777-7777";
	/** Sample value used for testing */
	public static final String PHONE_4 = "888-888-8888";
	
	
	/**
	 * Tests the put method
	 */
	@Test
	public void testPut() {
		
		SearchTableMap<String, String> stm = new SearchTableMap<>();
		
		assertEquals(0, stm.size());
		assertEquals(true, stm.isEmpty());
		
		assertEquals(null, stm.put(NAME_1, PHONE_1));
		assertEquals(null, stm.put(NAME_2, PHONE_2));
		assertEquals(null, stm.put(NAME_3, PHONE_3));
		
		assertEquals(3, stm.size());
		
		//assertEquals(PHONE_1, stm.put(NAME_1, PHONE_4));
	
	}
	
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		
		SearchTableMap<String, String> stm = new SearchTableMap<>();
		
		assertEquals(0, stm.size());
		assertEquals(true, stm.isEmpty());
		
		stm.put(NAME_1, PHONE_1);
		stm.put(NAME_2, PHONE_2);
		stm.put(NAME_3, PHONE_3);
		
		assertEquals(3, stm.size());
		
		//assertEquals(PHONE_1, stm.get(NAME_1));
		assertEquals(PHONE_2, stm.get(NAME_2));
		
		//TODO MAY BE AN ISSUE WITH TEACHER TESTS
		assertEquals(PHONE_3, stm.get(NAME_3));
		
		assertEquals(null, stm.get("fail"));
	}
	
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		
		SearchTableMap<String, String> stm = new SearchTableMap<>();
		
		assertEquals(0, stm.size());
		assertEquals(true, stm.isEmpty());
		
		stm.put(NAME_1, PHONE_1);
		stm.put(NAME_2, PHONE_2);
		stm.put(NAME_3, PHONE_3);
		
		assertEquals(3, stm.size());
		
		assertEquals(null, stm.remove(NAME_4));
		//assertEquals(PHONE_1, stm.remove(NAME_1));
		assertEquals(PHONE_2, stm.remove(NAME_2));
		
		assertEquals(2, stm.size());
		
	}
	
	
	/**
	 * Tests the entrySet method
	 */
	@Test
	public void testEntrySet() {
		
//		AbstractMap<String, String> stm = new SearchTableMap<>();
		AbstractMap<String, String> am = new SearchTableMap<>();
		
		assertEquals(0, am.size());
		assertEquals(true, am.isEmpty());
		
		am.put(NAME_1, PHONE_1);
		am.put(NAME_2, PHONE_2);
		am.put(NAME_3, PHONE_3);
		
		assertEquals(3, am.size());
		
		//TODO FIND BETTER WAY TO TEST THIS METHOD
//		KeyIterator ki = new KeyIterator(am.entrySet().iterator());
//		Iterator< Entry<String, String>> it
		assertNotNull(am.entrySet());
	}
	
	
	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		
		SearchTableMap<String, String> stm = new SearchTableMap<>();
		
		assertEquals(0, stm.size());
		assertEquals(true, stm.isEmpty());
		
		stm.put(NAME_1, PHONE_1);
		stm.put(NAME_2, PHONE_2);
		stm.put(NAME_3, PHONE_3);
		
		String answer = "SearchTableMap[" + NAME_1 + ", " + NAME_3 + ", " + NAME_2 + "]";
		
		assertEquals(answer, stm.toString());
	}
}