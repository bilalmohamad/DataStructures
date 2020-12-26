package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Interface used for creating AdapatablePriorityQueues
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K> the key
 * @param <V> the value
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * Removes the Entry from the PriorityQueue then repositions the queue accordingly
	 * 
	 * @param entry	the entry being removed
	 */
    void remove(Entry<K, V> entry);
    
    
    /**
     * Replaces the entered entry's key to the entered key
     * 
     * @param entry the entry to have the key replaced
     * @param key	the new key for the entry
     */
    void replaceKey(Entry<K, V> entry, K key);
    
    
    /**
     * Replaces the entered entry's value to the entered value
     * 
     * @param entry the entry to have the value replaced
     * @param value	the new value for the entry
     */
    void replaceValue(Entry<K, V> entry, V value);   
}
