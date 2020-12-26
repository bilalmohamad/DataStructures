package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * The interface used for creating Disjoint Sets
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <E> the object type
 */
public interface DisjointSet<E> {
	
	/**
	 * Creates a disjoint set that contains the element, then returns the position that identifies the set
	 * 
	 * @param value the element to be stored in the set
	 * 
	 * @return the position of the set
	 */
    Position<E> makeSet(E value);
    
    
    /**
     * Returns the position of the identifier for the disjoint set that contains the element
     * 
     * @param value	the element used for searching
     * 
     * @return the position of the set
     */
    Position<E> find(E value);
    
    
    /**
     * Merges the disjoint sets that contain positions s and t
     * 
     * @param s	the first set
     * @param t	the second set
     */
    void union(Position<E> s, Position<E> t);
}