package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

//Remember that search trees are ordered, so our elements must be Comparable
/**
 * Creates a set using the Tree Set strategy
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <E> the object type
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

	/** The map containing the tree */
	private Map<E, E> tree;

	
	/**
	 * The constructor used for creating TreeSet objects
	 */
	public TreeSet() {
		tree = new RedBlackTreeMap<E, E>();
	}

	
	@Override
	public Iterator<E> iterator() {
		return tree.iterator();
	}

	
	@Override
	public void add(E value) {
		tree.put(value, value);
	}

	
	@Override
	public boolean contains(E value) {
		
		for (E element : tree) {
			if (element.equals(value)) {
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public E remove(E value) {
		return tree.remove(value);
	}

	
	@Override
	public int size() {
		return tree.size();
	}
}
