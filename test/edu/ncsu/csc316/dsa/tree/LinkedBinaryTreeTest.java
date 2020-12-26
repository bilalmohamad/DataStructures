package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the LinkedBinaryTree class
 * 
 * @author Bilal Mohamad
 *
 */
public class LinkedBinaryTreeTest {
	
	/**
	 * Tests all of the methods in the LinkedBinaryTree class as well as its abstract classes
	 */
	@Test
	public void testLinkedBinaryTree() {
		
		LinkedBinaryTree<String> lbt = new LinkedBinaryTree<String>();
		
		assertEquals(true, lbt.isEmpty());
		assertEquals(0, lbt.size());
		
		
		Position<String> root = lbt.addRoot("Food");
		assertEquals("Food", root.getElement());
		assertEquals(true, lbt.isRoot(root));
		assertEquals(false, lbt.isInternal(root));
		
		
		Position<String> first = lbt.addLeft(root, "Meat");
		Position<String> second = lbt.addRight(root, "Fruit");
		
		assertEquals(2, lbt.numChildren(root));
		assertEquals(true, lbt.isLeaf(first));
		assertEquals(true, lbt.isLeaf(second));
		assertEquals(false, lbt.isLeaf(root));
		assertEquals(true, lbt.isInternal(root));
		assertEquals("Meat", lbt.left(root).getElement());
		assertEquals("Fruit", lbt.right(root).getElement());
		assertEquals(root, lbt.parent(first));
		assertEquals(first, lbt.sibling(second));
		assertEquals(second, lbt.sibling(first));
		
		
		Position<String> temp = lbt.addLeft(first, "Veggies");
		assertEquals("Veggies", lbt.remove(temp));
		
		
		assertEquals(first, lbt.setRoot(first));
		
		
		String[] order = {"Meat", "Food", "Fruit"};
		Iterator<Position<String>> it = lbt.inOrder().iterator();
		
		int index = 0;
		while (it.hasNext()) {
			assertEquals(order[index], it.next().getElement());
			index++;
		}
		
		
		assertEquals("Fruit", lbt.set(second, "Veggies"));
		
		assertNotNull(lbt.iterator());
		assertNotNull(lbt.children(root));
	}
}
