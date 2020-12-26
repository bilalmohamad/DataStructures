package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests the RedBlackTreeMap class
 * 
 * @author Bilal Mohamad
 *
 */
public class RedBlackTreeMapTest {
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
	 * Tests the methods of the RedBlackTreeMap class.
	 * Tests the methods of the BinarySearchTreeMap super class
	 */
	@Test
	public void testRedBlackTreeMap() {
		
		BinarySearchTreeMap<String, String> tree = new RedBlackTreeMap<String, String>();
		assertEquals(0, tree.size());
		assertEquals(true, tree.isEmpty());
		
		
		tree.put(NAME_1, PHONE_1);
		tree.put(NAME_2, PHONE_2);
		tree.put(NAME_3, PHONE_3);
		tree.put(NAME_4, PHONE_4);
		tree.put(NAME_5, PHONE_5);
		tree.put(NAME_6, PHONE_6);
		tree.put(NAME_7, PHONE_7);
		assertEquals(7, tree.size());
		
		assertEquals(PHONE_1, tree.get(NAME_1));
		assertEquals(PHONE_2, tree.get(NAME_2));
		assertEquals(PHONE_3, tree.get(NAME_3));
		assertEquals(PHONE_4, tree.get(NAME_4));
		
		
		
		Position<Entry<String, String>> root = tree.root();
		assertEquals(2, tree.numChildren(root));
		
		tree.actionOnInsert(root);
		assertEquals(7, tree.size());
		
		tree.actionOnDelete(tree.right(root));
		tree.actionOnDelete(root);
		assertEquals(7, tree.size());
		
		tree.actionOnAccess(root);
		assertEquals(7, tree.size());
		
		assertNotNull(tree.toString());
		assertNotNull(tree.children(root));
		assertNotNull(tree.preOrder());
		assertNotNull(tree.postOrder());
		assertNotNull(tree.levelOrder());
		assertNotNull(tree.inOrder());
		
		
		Iterator<Entry<String, String>> it = tree.entrySet().iterator();
		
		assertEquals(PHONE_1, it.next().getValue());
		assertEquals(PHONE_2, it.next().getValue());
		assertEquals(PHONE_3, it.next().getValue());
		assertEquals(PHONE_4, it.next().getValue());
	}
	
	
	/**
	 * Tests the remove method of BinarySearchTreeMap
	 */
	@Test
	public void testRemove() {
		
		BinarySearchTreeMap<String, String> tree = new RedBlackTreeMap<String, String>(null);
		assertEquals(0, tree.size());
		assertEquals(true, tree.isEmpty());
		
		
		tree.put(NAME_1, PHONE_1);
		tree.put(NAME_2, PHONE_2);
		tree.put(NAME_3, PHONE_3);
		tree.put(NAME_4, PHONE_4);
		tree.put(NAME_5, PHONE_5);
		tree.put(NAME_6, PHONE_6);
		tree.put(NAME_7, PHONE_7);
		assertEquals(7, tree.size());
		
		assertEquals(PHONE_3, tree.remove(NAME_3));
	}
}

