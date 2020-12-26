package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Creates a hash map using the Linear Probing strategy
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/** An array of TableEntry objects */
    private TableEntry<K, V>[] table;
    
    /** The size of the hash map */
    private int size;

    
    /**
     * The constructor used for creating LinearProbingHashMap objects without a specified capacity
     */
    public LinearProbingHashMap() {
        super();
    }
    
    
    /**
     * The constructor used for creating LinearProbingHashMap objects with a specified capacity
     * 
     * @param capacity	the capacity of the LinearProbingHashMap
     */
    public LinearProbingHashMap(int capacity) {
        super(capacity);
        size = 0;
    }

    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> buffer = new ArrayBasedList<>();
        
        for (int i = 0; i < capacity(); i++) {
        	if (!isAvailable(i)) {
        		buffer.addLast(table[i]);
        	}
        }
        
        return buffer;
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    
    /**
     * Helper method to determine whether a bucket has an entry or not
     * 
     * @param index	the index being observed
     * 
     * @return 	true	if the table entry is available
     * 			false	otherwise
     */
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    // Helper method to find the bucket for an entry;
    // If the entry *is* in the map, returns the index of the bucket
    // If the entry is *not* in the map, returns -(a + 1) to indicate 
    //     that the entry should be added at index a
    /**
     * Helper method to find the bucket for an entry
     * 
     * @param index	the index being observed
     * @param key	the key being observed
     * 
     * @return	the int value of the bucket
     */
    private int findBucket(int index, K key) {
    	
        int avail = -1;
        int j = index;
        
        do {
        	if (isAvailable(j)) {
        		
        		if (avail == -1) {
        			avail = j;
        		}
        		
        		if (table[j] == null) {
        			break;
        		}
        		
        	}
        	else if (table[j].getKey().equals(key)) {
        		return j;
        	}
        	
        	j = (j + 1) % capacity();
        	
        } while (j != index);
        
        return -(avail + 1);
    }    

    
    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        
        if (index < 0) {
        	return null;
        }
        
        return table[index].getValue();
    }
    

    @Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        
        if (index >= 0) {
        	return table[index].setValue(value);
        }
        
        table[-(index + 1)] = new TableEntry<K, V>(key, value);
        size++;
        
        return null;
    }

    
    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        
        if (index < 0) {
        	return null;
        }
        
        V answer = table[index].getValue();
        table[index].setDeleted(true);
        size--;
        
        return answer;
        
    }

    
    @Override
    public int size() {
        return size;
    }

    
    @Override
    protected int capacity() {
        return table.length;
    }
    
    
    /**
     * Inner class used for creating TableEntry objects
     *
     * @author Bilal Mohamad (bmohama)
     *
     * @param <K>	the type of the key
     * @param <V>	the type of the value
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** A check for whether the entry has been removed or not */
        private boolean isDeleted;

        
        /**
         * Constructor used for creating TableEntry objects
         * 
         * @param key	the key of the entry
         * @param value	the value of the entry
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        
        /**
         * Checks if the entry has been removed or not
         * 
         * @return	true	if the entry has been removed
         * 			false	otherwise
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        
        /**
         * Sets the isDeleted variable to be the deleted parameter
         * 
         * @param deleted	the new value for the isDeleted variable
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
