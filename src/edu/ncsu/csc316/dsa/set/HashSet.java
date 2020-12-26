package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

//Since our hash map uses linear probing, the entries are not ordered.
//As a result, we do not restrict our hash set to use Comparable elements.
//This also gives you an option if you need a set to manage elements
//  that are *NOT* Comparable (versus a TreeSet)
/**
 * Creates a set using the Hash Set strategy
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <E> the object type
 */
public class HashSet<E> extends AbstractSet<E> {

	/** The map containing the hash table */
	private Map<E, E> map;

	
	/**
	 * The constructor used for creating HashSet objects
	 */
	public HashSet() {
		map = new LinearProbingHashMap<E, E>();
	}

	
	@Override
	public Iterator<E> iterator() {
		return map.iterator();
	}

	
	@Override
	public void add(E value) {
		map.put(value, value);
	}

	
	@Override
	public boolean contains(E value) {
		
		for (E element : map) {
			if (element.equals(value)) {
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public E remove(E value) {
		return map.remove(value);
	}

	
	@Override
	public int size() {
		return map.size();
	}
}