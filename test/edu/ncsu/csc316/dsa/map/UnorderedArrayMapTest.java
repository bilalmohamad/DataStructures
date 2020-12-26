package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

/**
 * Tests the UnorderedArrayMap class
 * 
 * @author Bilal Mohamad
 *
 */
public class UnorderedArrayMapTest {

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
		
		UnorderedArrayMap<String, String> uam = new UnorderedArrayMap<>();
		
		assertEquals(0, uam.size());
		assertEquals(true, uam.isEmpty());
		
		assertEquals(null, uam.put(NAME_1, PHONE_1));
		assertEquals(null, uam.put(NAME_2, PHONE_2));
		assertEquals(null, uam.put(NAME_3, PHONE_3));
		
		assertEquals(3, uam.size());
		
		assertEquals(PHONE_1, uam.put(NAME_1, PHONE_4));
	
	}
	
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		
		UnorderedArrayMap<String, String> uam = new UnorderedArrayMap<>();
		
		assertEquals(0, uam.size());
		assertEquals(true, uam.isEmpty());
		
		uam.put(NAME_1, PHONE_1);
		uam.put(NAME_2, PHONE_2);
		uam.put(NAME_3, PHONE_3);
		
		assertEquals(3, uam.size());
		
		assertEquals(PHONE_1, uam.get(NAME_1));
		assertEquals(PHONE_2, uam.get(NAME_2));
		assertEquals(PHONE_3, uam.get(NAME_3));
		
		assertEquals(null, uam.get("fail"));
	}
	
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		
		UnorderedArrayMap<String, String> uam = new UnorderedArrayMap<>();
		
		assertEquals(0, uam.size());
		assertEquals(true, uam.isEmpty());
		
		uam.put(NAME_1, PHONE_1);
		uam.put(NAME_2, PHONE_2);
		uam.put(NAME_3, PHONE_3);
		
		assertEquals(3, uam.size());
		
		assertEquals(null, uam.remove(NAME_4));
		assertEquals(PHONE_1, uam.remove(NAME_1));
		assertEquals(PHONE_2, uam.remove(NAME_2));
		
		assertEquals(1, uam.size());
		
	}
	
	
	/**
	 * Tests the entrySet method
	 */
	@Test
	public void testEntrySet() {
		
//		AbstractMap<String, String> uam = new UnorderedArrayMap<>();
		AbstractMap<String, String> am = new UnorderedArrayMap<>();
		
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
		
		UnorderedArrayMap<String, String> uam = new UnorderedArrayMap<>();
		
		assertEquals(0, uam.size());
		assertEquals(true, uam.isEmpty());
		
		uam.put(NAME_1, PHONE_1);
		uam.put(NAME_2, PHONE_2);
		uam.put(NAME_3, PHONE_3);
		
		String answer = "UnorderedArrayMap[" + NAME_3 + ", " + NAME_2 + ", " + NAME_1 + "]";
		
		assertEquals(answer, uam.toString());
	}
	
	
	/**
	 * Tests the KeyIterator and ValueIterator inner classes of the AbstractMap class.
	 */
	@Test
	public void testIterators() {
		
		UnorderedArrayMap<String, String> uam = new UnorderedArrayMap<>();
		
		assertEquals(0, uam.size());
		assertEquals(true, uam.isEmpty());
		
		uam.put(NAME_1, PHONE_1);
		uam.put(NAME_2, PHONE_2);
		uam.put(NAME_3, PHONE_3);
		
		assertEquals(3, uam.size());
		assertEquals(false, uam.isEmpty());
		
		Iterator<String> names = uam.iterator();
		
		while(names.hasNext()) {
			assertNotNull(names.next());
		}
		
		try {
			names.remove();
			fail();
		}
		catch (UnsupportedOperationException e) {
			assertEquals("The remove operation is not supported yet.", e.getMessage());
		}
		
		Iterator<String> phones = uam.values().iterator();
		
		while(phones.hasNext()) {
			assertNotNull(phones.next());
		}
		
		try {
			phones.remove();
			fail();
		}
		catch (UnsupportedOperationException e) {
			assertEquals("The remove operation is not supported yet.", e.getMessage());
		}
	}
}
