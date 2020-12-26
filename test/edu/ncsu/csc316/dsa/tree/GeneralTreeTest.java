package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the GeneralTree class
 * 
 * @author Bilal Mohamad
 *
 */
public class GeneralTreeTest {
	
	/**
	 * Tests all of the methods in the GeneralTree class as well as its abstract classes
	 */
	@Test
	public void testGeneralTree() {
		
		GeneralTree<String> gt = new GeneralTree<String>();
		
		assertEquals(true, gt.isEmpty());
		assertEquals(0, gt.size());
		
		
		Position<String> root = gt.addRoot("Food");
		assertEquals("Food", root.getElement());
		assertEquals(true, gt.isRoot(root));
		assertEquals(false, gt.isInternal(root));
		
		
		Position<String> first = gt.addChild(root, "Meat");
		Position<String> second = gt.addChild(root, "Fruit");
		Position<String> third = gt.addChild(root, "Fruit");
		
		assertEquals(3, gt.numChildren(root));
		assertEquals(true, gt.isLeaf(first));
		assertEquals(true, gt.isLeaf(second));
		assertEquals(true, gt.isLeaf(third));
		assertEquals(false, gt.isLeaf(root));
		assertEquals(true, gt.isInternal(root));
		
		
		assertEquals("Fruit", gt.set(second, "Veggies"));
		
		
		Position<String> temp = gt.addChild(root, "Fruit");
		assertEquals("Fruit", gt.remove(temp));
		
		
		String[] order = {"Food", "Meat", "Veggies", "Fruit"};
		Iterator<Position<String>> it = gt.preOrder().iterator();
		
		int index = 0;
		while (it.hasNext()) {
			assertEquals(order[index], it.next().getElement());
			index++;
		}
		
		
		Iterator<Position<String>> it2 = gt.postOrder().iterator();
		
		assertEquals("Meat", it2.next().getElement());
		assertEquals("Veggies", it2.next().getElement());
		assertEquals("Fruit", it2.next().getElement());
		assertEquals("Food", it2.next().getElement());
		
		
		Iterator<Position<String>> it3 = gt.levelOrder().iterator();
		
		index = 0;
		while (it3.hasNext()) {
			assertEquals(order[index], it3.next().getElement());
			index++;
		}
		
		//From GeneralTree not from Abstract class
		Iterator<String> it4 = gt.iterator();
		try {
			it4.remove();
			fail();
		}
		catch (UnsupportedOperationException e) {
			assertEquals("The remove operation is not supported yet.", e.getMessage());
		}
		
		index = 0;
		while (it4.hasNext()) {
			assertEquals(order[index], it4.next());
			index++;
		}
		
		
		assertNotNull(gt.toString());
	}
}
