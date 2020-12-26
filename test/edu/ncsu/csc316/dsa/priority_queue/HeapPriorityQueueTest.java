package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc316.dsa.priority_queue.AbstractPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Tests the HeapPriorityQueue class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class HeapPriorityQueueTest {

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
	 * Tests the methods in the HeapPriorityQueue class for coverage
	 */
	@Test
	public void testHeapPriorityQueue() {
		
		AbstractPriorityQueue<String, String> apq = new HeapPriorityQueue<String, String>();
		assertEquals(true, apq.isEmpty());
		assertEquals(0, apq.size());
		assertEquals(null, apq.min());
		assertEquals(null, apq.deleteMin());
		
		
		//For insert
		assertEquals(NAME_1, apq.insert(NAME_1, PHONE_1).getKey());
		assertEquals(NAME_1, apq.min().getKey());
		
		assertEquals(NAME_2, apq.insert(NAME_2, PHONE_2).getKey());
		assertEquals(NAME_1, apq.min().getKey());
		
		assertEquals(NAME_3, apq.insert(NAME_3, PHONE_3).getKey());
		assertEquals(NAME_1, apq.min().getKey());
		
		assertEquals(NAME_4, apq.insert(NAME_4, PHONE_4).getKey());
		assertEquals(NAME_1, apq.min().getKey());
		
		assertEquals(false, apq.isEmpty());
		assertEquals(4, apq.size());
		
		
		//For deleteMin
		Entry<String, String> temp = apq.deleteMin();
		assertEquals(NAME_1, temp.getKey());
		assertEquals(PHONE_1, temp.getValue());
		assertEquals(3, apq.size());
		
		temp = apq.deleteMin();
		assertEquals(NAME_3, temp.getKey());
		assertEquals(PHONE_3, temp.getValue());
		assertEquals(2, apq.size());
		
		temp = apq.deleteMin();
		assertEquals(NAME_2, temp.getKey());
		assertEquals(PHONE_2, temp.getValue());
		assertEquals(1, apq.size());
		
		temp = apq.deleteMin();
		assertEquals(NAME_4, temp.getKey());
		assertEquals(PHONE_4, temp.getValue());
		assertEquals(0, apq.size());
		
		assertEquals(true, apq.isEmpty());
	}
}
