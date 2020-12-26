package edu.ncsu.csc316.dsa.list;

/**
 * The interface implemented by classes creating a custom list
 * 
 * @author Bilal Mohamad 
 *
 * @param <E> The object type of the list.
 */
public interface List<E> extends Iterable<E> {
	
	/**
	 * Adds the value at the specified index to list
	 * 
	 * @param index	the index of the list
	 * @param value	the value being added to the list
	 */
    void add(int index, E value);
    
    
    /**
     * Adds a value to the first index of the list
     * 
     * @param value	the value being added to the list
     */
    void addFirst(E value);
    
    
    /**
     * Adds a value to the last index of the list
     * 
     * @param value	the value being added to the list
     */
    void addLast(E value);
    
    
    /**
     * Retrieves the first element in the list
     * 
     * @return the first element in the list
     */
    E first();
    
    
    /**
     * Retrieves the element at the specified index in the list
     * 
     * @param index	the index of the list
     * 
     * @return	the element at the specified index
     */
    E get(int index);
    
    
    /**
     * Determines whether the list is empty or not
     * 
     * @return 	true	if the list is empty
     * 			false	if the list is not
     */
    boolean isEmpty();
    
    
    /**
     * Retrieves the last element in the list
     * 
     * @return the last element in the list
     */
    E last();
    
    
    /**
     * Removes the element at the specified index
     * 
     * @param index the index of the list
     * 
     * @return the element removed
     */
    E remove(int index);
    
    
    /**
     * Removes the element at the first index
     * 
     * @return the element removed
     */
    E removeFirst();
    
    
    /**
     * Removes the element at the last index
     * 
     * @return the element removed
     */
    E removeLast();
    
    
    /**
     * Sets the element at the specified index to become the entered value
     * 
     * @param index	the index of the list
     * @param value the value being set to the list
     * 
     * @return the original element in the list before the change was made
     */
    E set(int index, E value);
    
    
    /**
     * Retrieves the size of the list
     * 
     * @return the size of the list
     */
    int size();
}