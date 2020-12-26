package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Interface for creating a Priority Queue
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K> the key
 * @param <V> the value
 */
public interface PriorityQueue<K, V> {

	/**
	 * Inner interface for creating each individual entry of the priority queue
	 *
	 * @author Bilal Mohamad (bmohama)
	 *
	 * @param <K> the key
	 * @param <V> the value
	 */
    interface Entry<K, V> {
    	
    	/**
    	 * Retrieves the key of the entry
    	 * 
    	 * @return the key of the entry
    	 */
        K getKey();
        
    	/**
    	 * Retrieves the value of the entry
    	 * 
    	 * @return the value of the entry
    	 */
        V getValue();
    }
    
    /**
     * Inserts an entry with the entered key and value into the PriorityQueue
     * 
     * @param key		the key being inserted
     * @param value		the value being inserted
     * 
     * @return the entry that was created for insertion
     */
    Entry<K, V> insert(K key, V value);
    
    /**
     * Retrieves the minimum Entry of the PriorityQueue
     * 
     * @return the minimum Entry of the PriorityQueue
     */
    Entry<K, V> min();
    
    /**
     * Deletes the minimum Entry of the PriorityQueue
     * 
     * @return the minimum Entry of the PriorityQueue that was removed
     */
    Entry<K, V> deleteMin();
    
    /**
     * Retrieves the size of the PriorityQueue
     * 
     * @return the size of the PriorityQueue
     */
    int size();
    
    /**
     * Checks if the PriorityQueue contains any elements
     * 
     * @return	true	if it contains no elements
     * 			false	if there are any elements
     */
    boolean isEmpty();
}
