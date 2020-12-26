package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * The interface implemented by classes attempting to a positional list
 * 
 * @author Bilal Mohamad 
 *
 * @param <E> The object type of the list.
 */
public interface PositionalList<E> extends Iterable<E> {
    
	/**
	 * Adds the value after the entered position
	 * 
	 * @param p		the position to add after
	 * @param value	the value to be added
	 * 
	 * @return the position of the added element
	 */
    Position<E> addAfter(Position<E> p, E value);
    
    
	/**
	 * Adds the value before the entered position
	 * 
	 * @param p		the position to add before
	 * @param value	the value to be added
	 * 
	 * @return the position of the added element
	 */
    Position<E> addBefore(Position<E> p, E value);
    
    
	/**
	 * Adds the value after the first position
	 * 
	 * @param value	the value to be added
	 * 
	 * @return the position of the added element
	 */
    Position<E> addFirst(E value);
    
    
	/**
	 * Adds the value after the last position
	 * 
	 * @param value	the value to be added
	 * 
	 * @return the position of the added element
	 */
    Position<E> addLast(E value);
    
    
    /**
     * Retrieves the position after the entered position
     * 
     * @param p	the position being observed
     * 
     * @return the position after the entered position
     */
    Position<E> after(Position<E> p);
    
    
    /**
     * Retrieves the position before the entered position
     * 
     * @param p	the position being observed
     * 
     * @return the position before the entered position
     */
    Position<E> before(Position<E> p);
    
    
    /**
     * Retrieves the first position the entered position
     * 
     * @return the first position the entered position
     */
    Position<E> first();
    
    
    /**
     * Determines whether the list is empty or not
     * 
     * @return 	true	if the list is empty
     * 			false	if the list is not
     */
    boolean isEmpty();
    
    
    /**
     * Retrieves the last position the entered position
     * 
     * @return the last position the entered position
     */
    Position<E> last();
    
    
    /**
     * Retrieves the iterator's position
     * 
     * @return the iterator's position
     */
    Iterable<Position<E>> positions();
    
    
    /**
     * Removes the element at the entered position
     * 
     * @param p the position being observed
     * 
     * @return the element removed from the list
     */
    E remove(Position<E> p);
    
    
    /**
     * Sets the element to the value at the entered position
     * 
     * @param p 	the position to be changed
     * @param value	the value to change the current element to
     * 
     * @return the element that was originally at the position
     */
    E set(Position<E> p, E value);
    
    
    /**
     * Retrieves the size of the list
     * 
     * @return the size of the list
     */
    int size();
}
