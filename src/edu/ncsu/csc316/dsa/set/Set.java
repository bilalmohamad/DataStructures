package edu.ncsu.csc316.dsa.set;

/**
 * The interface to be implemented by any class attempting to create a Set
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <E> the object type
 */
public interface Set<E> extends Iterable<E> {
	
	/**
	 * Adds the element to the set (if the element is not already present in the set)
	 * 
	 * @param value the element to be added
	 */
    void add(E value);
    
    
    /**
     * Returns true if the set contains the element; otherwise, returns false
     * 
     * @param value the element being observed
     * 
     * @return	true	if the set contains the element
     * 			false	otherwise
     */
    boolean contains(E value);
    
    
    /**
     * Removes and returns the element from the set (if the element is present in the set)
     * 
     * @param value	the element to be removed
     * 
     * @return the 
     */
    E remove(E value);
    
    
    /**
     * Updates the current set to also include all elements contained in the other set (also called “union”)
     * 
     * @param other the set being added to the current set
     */
    void addAll(Set<E> other);
    
    
    /**
     * Updates the current set to keep only those elements that are also elements in the other set(also called “intersection”)
     * 
     * @param other the second set used for intersection
     */
    void retainAll(Set<E> other);
    
    
    /**
     * Updates the current set to remove any of the elements that are contained in other set (also called “subtraction”)
     * 
     * @param other the seconds set used for subtraction
     */
    void removeAll(Set<E> other);
    
    
    /**
     * Returns the number of elements in the set
     * 
     * @return the number of elements in the set
     */
    int size();
    
    
    /**
     * Checks if the set contains any elements
     * 
     * @return	true	if the set does not contain any elements
     * 			false	otherwise
     */
    boolean isEmpty();
}
