package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Abstract class used by other classes for implementation of a PriorityQueue.
 * Contains all basic methods for any PriorityQueue
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K> the key
 * @param <V> the value
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	/** The comparator used for comparison */
    private Comparator<K> comparator;

    /**
     * The constructor used for creating AbstractPriorityQueues
     * 
     * @param c	the comparator used for comparison
     */
    public AbstractPriorityQueue(Comparator<K> c) {
        setComparator(c);
    }
    
    
    /**
     * Sets the Comparator to the entered parameter
     * 
     * @param c the comparator to set the current one to
     */
    private void setComparator(Comparator<K> c) {
        if(c == null) {
            c = new NaturalOrder();
        }
        comparator = c;
    }

    
    /**
     * Inner class used for determining how to order different elements
     *
     * @author Bilal Mohamad (bmohama)
     *
     */
    public class NaturalOrder implements Comparator<K> {
    	
    	/**
    	 * Used to compare to Keys and determine the order of the two keys
    	 * 
    	 * @param first		the first key being compared
    	 * @param second	the second key being compared
    	 * 
    	 * @return	a value greater than, less than, or equal to zero based on the order of the two keys
    	 */
        public int compare(K first, K second) {
            return ((Comparable<K>) first).compareTo(second);
        }
    }

    
	/**
	 * Used to compare to Keys and determine the order of the two keys
	 * 
	 * @param data1	the first key being compared
	 * @param data2	the second key being compared
	 * 
	 * @return	a value greater than, less than, or equal to zero based on the order of the two keys
	 */
    public int compare(K data1, K data2) {
        return comparator.compare(data1, data2);
    }
    
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    
    /**
     * Creates a PriorityQueue Entry
     *
     * @author Bilal Mohamad (bmohama)
     *
	 * @param <K> the key
	 * @param <V> the value
     */
    public static class PQEntry<K, V> implements Entry<K, V> {

    	/** The key of the Entry */
        private K key;
        
        /** The value of the Entry */
        private V value;

        
        /**
         * The constructor used for creating an Entry
         * 
         * @param key	the key of the Entry
         * @param value the value of the Entry
         */
        public PQEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        
        /**
         * Sets the Entry's key to the entered key
         * 
         * @param key	the key being set to
         */
        public void setKey(K key) {
            this.key = key;
        }

        
        /**
         * Sets the Entry's value to the entered value
         * 
         * @param value	the value being set to
         */
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
    
    /**
     * Factory method for constructing a new priority queue entry object
     * 
     * @param key	the key of the new Entry
     * @param value the value of the new Entry
     * 
     * @return the new Entry
     */
    protected Entry<K, V> createEntry(K key, V value) {
        return new PQEntry<K, V>(key, value);
    }
}