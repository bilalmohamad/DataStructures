package edu.ncsu.csc316.dsa.queue;

/**
 * This interface will be implemented classes that will use an abstract list
 * as a Queue.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type of the list.
 */
public interface Queue<E> {
	
	/**
	 * Adds the entered value to the end of the list
	 * @param value	the value to be added
	 */
    void enqueue(E value);
    
    
    /**
     * Removes the element at the front of the list
     * @return	the element that was removed
     */
    E dequeue();
    
    
    /**
     * Retrieves the element at the front of the list
     * @return	the element at the front of the list
     */
    E front();
    
    
    /**
     * Retrieve the size of the list
     * @return	the size of the list
     */
    int size();
    
    
    /**
     * Determines whether the list is empty or not
     * 
     * @return	true	if the list is empty
     * 			false	if the list is not empty
     */
    boolean isEmpty();
}