package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Tests the UnorderedLinkedMap class
 * 
 * @author Bilal Mohamad
 *
 */
public class UnorderedLinkedMapTest {

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
		
		UnorderedLinkedMap<String, String> ulm = new UnorderedLinkedMap<>();
		
		assertEquals(0, ulm.size());
		assertEquals(true, ulm.isEmpty());
		
		assertEquals(null, ulm.put(NAME_1, PHONE_1));
		assertEquals(null, ulm.put(NAME_2, PHONE_2));
		assertEquals(null, ulm.put(NAME_3, PHONE_3));
		
		assertEquals(3, ulm.size());
		
		assertEquals(PHONE_1, ulm.put(NAME_1, PHONE_4));
	
	}
	
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		
		UnorderedLinkedMap<String, String> ulm = new UnorderedLinkedMap<>();
		
		assertEquals(0, ulm.size());
		assertEquals(true, ulm.isEmpty());
		
		ulm.put(NAME_1, PHONE_1);
		ulm.put(NAME_2, PHONE_2);
		ulm.put(NAME_3, PHONE_3);
		
		assertEquals(3, ulm.size());
		
		assertEquals(PHONE_1, ulm.get(NAME_1));
		assertEquals(PHONE_2, ulm.get(NAME_2));
		assertEquals(PHONE_3, ulm.get(NAME_3));
		
		assertEquals(null, ulm.get("fail"));
	}
	
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		
		UnorderedLinkedMap<String, String> ulm = new UnorderedLinkedMap<>();
		
		assertEquals(0, ulm.size());
		assertEquals(true, ulm.isEmpty());
		
		ulm.put(NAME_1, PHONE_1);
		ulm.put(NAME_2, PHONE_2);
		ulm.put(NAME_3, PHONE_3);
		
		assertEquals(3, ulm.size());
		
		assertEquals(null, ulm.remove(NAME_4));
		assertEquals(PHONE_1, ulm.remove(NAME_1));
		assertEquals(PHONE_2, ulm.remove(NAME_2));
		
		assertEquals(1, ulm.size());
		
	}
	
	
	/**
	 * Tests the entrySet method
	 */
	@Test
	public void testEntrySet() {
		
//		AbstractMap<String, String> ulm = new UnorderedLinkedMap<>();
		AbstractMap<String, String> am = new UnorderedLinkedMap<>();
		
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
		
		UnorderedLinkedMap<String, String> ulm = new UnorderedLinkedMap<>();
		
		assertEquals(0, ulm.size());
		assertEquals(true, ulm.isEmpty());
		
		ulm.put(NAME_1, PHONE_1);
		ulm.put(NAME_2, PHONE_2);
		ulm.put(NAME_3, PHONE_3);
		
		String answer = "UnorderedLinkedMap[" + NAME_3 + ", " + NAME_2 + ", " + NAME_1 + "]";
		
		assertEquals(answer, ulm.toString());
	}
}