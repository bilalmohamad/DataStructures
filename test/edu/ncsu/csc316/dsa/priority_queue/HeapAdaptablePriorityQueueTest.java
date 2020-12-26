package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Tests the HeapAdaptablePriorityQueue class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** Sample key used for testing */
	public static final String NAME_1 = "Bill Nye";
	/** Sample key used for testing */
	public static final String NAME_2 = "Bobby";
	/** Sample key used for testing */
	public static final String NAME_3 = "Billy";
	/** Sample key used for testing */
	public static final String NAME_4 = "Comp Sci";
	/** Sample key used for testing */
	public static final String NAME_5 = "Apples";
	/** Sample key used for testing */
	public static final String NAME_6 = "Donuts";
	
	/** Sample value used for testing */
	public static final String PHONE_1 = "555-555-5555";
	/** Sample value used for testing */
	public static final String PHONE_2 = "666-666-6666";
	/** Sample value used for testing */
	public static final String PHONE_3 = "777-777-7777";
	/** Sample value used for testing */
	public static final String PHONE_4 = "888-888-8888";
	/** Sample value used for testing */
	public static final String PHONE_5 = "999-999-9999";
	/** Sample value used for testing */
	public static final String PHONE_6 = "000-000-0000";
	
	
	/**
	 * Tests the methods in the HeapAdaptablePriorityQueue class for coverage
	 */
	@Test
	public void testHeapAdaptablePriorityQueue() {
		
		HeapAdaptablePriorityQueue<String, String> hapq = new HeapAdaptablePriorityQueue<String, String>();
		assertEquals(true, hapq.isEmpty());
		assertEquals(0, hapq.size());
		assertEquals(null, hapq.min());
		assertEquals(null, hapq.deleteMin());
		
		
		
		//For insert
		assertEquals(NAME_1, hapq.insert(NAME_1, PHONE_1).getKey());
		assertEquals(NAME_1, hapq.min().getKey());
		
		assertEquals(NAME_2, hapq.insert(NAME_2, PHONE_2).getKey());
		assertEquals(NAME_1, hapq.min().getKey());
		
		assertEquals(NAME_3, hapq.insert(NAME_3, PHONE_3).getKey());
		assertEquals(NAME_1, hapq.min().getKey());
		
		assertEquals(NAME_4, hapq.insert(NAME_4, PHONE_4).getKey());
		assertEquals(NAME_1, hapq.min().getKey());
		
		assertEquals(false, hapq.isEmpty());
		assertEquals(4, hapq.size());
		
		
		
		//For replaceKey and replaceValue
		Entry<String, String> replace = hapq.insert(NAME_6, PHONE_6);
		
		hapq.replaceKey(replace, NAME_5);
		hapq.replaceValue(replace, PHONE_5);
		
		assertEquals(NAME_5, hapq.min().getKey());
		assertEquals(PHONE_5, hapq.min().getValue());
		
		
		
		//For remove
		hapq.remove(replace);
		assertEquals(NAME_1, hapq.min().getKey());
	}
}
