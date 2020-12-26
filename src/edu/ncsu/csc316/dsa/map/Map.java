package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * This class will be implemented by any class trying to create a custom map.
 *  
 * @author Bilal Mohamad
 *
     * @param <K>	the type of the key
     * @param <V>	the type of the value
 */
public interface Map<K, V> extends Iterable<K> {
	
	/**
	 * Used to retrieve an iteratable of the current map. Prevents manipulation of the entry set
	 * 
	 * @return an iterable of the current map
	 */
    Iterable<Entry<K, V>> entrySet();
    
    /**
     * Retrieves the value at the entered key
     * 
     * @param key	the key to retrieve the value from
     * 
     * @return the value at the entered key
     */
    V get(K key);
    
    /**
     * Checks if the map contains any data
     * 
     * @return	true	if the map contains no data
     * 			false	if the map does contain data
     */
    boolean isEmpty();
    
    /**
     * Retrieves an iterator of the current map
     * 
     * @return an iterator for the current map
     */
    Iterator<K> iterator();
    
    /**
     * Inserts the value with the specified key. If the key already exists, then its value is
     * replaced by the entered value.
     * 
     * @param key	the key of the map entry
     * @param value	the value of the map entry
     * 
     * @return	null	if the key does not exist in the map yet
     * 			otherwise, the original value at the specified key
     */
    V put(K key, V value);
    
    /**
     * Removes the map entry at the entered key
     * 
     * @param key	the key of the map entry to be removed
     * 
     * @return	null	if the key does not exist in the map
     * 			otherwise, the value that was removed at the key
     */
    V remove(K key);
    
    /**
     * Retrieves the size of the map
     * 
     * @return the size of the map
     */
    int size();
    
    /**
     * Retrieves an iterable of the values
     * 
     * @return an iterable of the values
     */
    Iterable<V> values();
    
    
    /**
     * The inner interface used for setting and retrieving an individual map entry's value and key
     * 
     * @author Bilal Mohamad
     *
     * @param <K>	the type of the key
     * @param <V>	the type of the value
     */
    interface Entry<K, V> {
    	
    	/**
    	 * Retrieves the map entry's key
    	 * 
    	 * @return the key of the map entry
    	 */
        K getKey();
        
    	/**
    	 * Retrieves the map entry's value
    	 * 
    	 * @return the value of the map entry
    	 */
        V getValue();
        
        /**
         * Sets the current map entry to the entered key 
         * 
         * @param key	the key of the map entry
         * 
         * @return the original key of the current map entry	
         */
        K setKey(K key);
        
        /**
         * Sets the current map entry to the entered value
         * 
         * @param value	the value of the map entry
         * 
         * @return the original value of the current map entry	
         */
        V setValue(V value);
    }
}
