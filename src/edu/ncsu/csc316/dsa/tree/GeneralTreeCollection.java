package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface used for creating a General Tree by extending the Tree interface
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
	
	/**
	 * Adds a new root to the tree
	 * 
	 * @param value	 the value of the new root
	 * 
	 * @return the position of the new root
	 */
    Position<E> addRoot(E value);
    
    /**
     * Adds a child to the entered position with the entered value
     * 
     * @param p		the position of the child
     * @param value	the value of the child
     * 
     * @return	the new position of the child
     */
    Position<E> addChild(Position<E> p, E value);
    
    /**
     * Removes the tree node at the entered position
     * 
     * @param p	the position to remove
     * '
     * @return	the value of the removed position
     */
    E remove(Position<E> p);
    
    /**
     * Sets the entered position to the entered value
     * 
     * @param p		the position to modify
     * @param value	the new value for the position
     * 
     * @return	the original value at the specified position
     */
    E set(Position<E> p, E value);
}
