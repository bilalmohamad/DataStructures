package edu.ncsu.csc316.dsa.stack;

/**
 * This interface will be implemented classes that will use an abstract list
 * as a Stack.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type of the list.
 */
public interface Stack<E> {
	
	/**
	 * Adds the entered value to the front of the list
	 * @param value	the value to be added
	 */
    void push(E value);
    
    
    /**
     * Removes the first element in the list and returns the element that was remove
     * @return the element that was removed
     */
    E pop();
    
    
    /**
     * Retrieves the first element in the list
     * @return the first element in the list
     */
    E top();
    
    
    /**
     * Retrieves the size of the list
     * @return the size of the list
     */
    int size();
    
    
    /**
     * Indicates whether the list is empty or not
     * 
     * @return	true	if the list is empty
     * 			false	if the list is not
     */
    boolean isEmpty();
}
