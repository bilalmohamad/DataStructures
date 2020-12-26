package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * The abstract class used for creating a map that is already sorted
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	/** Used for comparison between two objects */
    private Comparator<K> compare;

    /**
     * The constructor used for creating AbstractSortedMap objects
     * 
     * @param compare	the type of comparator to be used
     */
    public AbstractSortedMap(Comparator<K> compare) {
        if (compare == null) {
            this.compare = new NaturalOrder();
        } else {
            this.compare = compare;
        }
    }

    /**
     * Compares two keys and returns a value depending on the order of precedence of the entered keys
     * 
     * @param key1	the first key for comparison
     * @param key2	the second key for comparison
     * 
     * @return	0	if the keys are the same
     * 			-1	if the first key is before the second key
     * 			1 	the if the second is before the first key
     */
    public int compare(K key1, K key2) {
        return compare.compare(key1, key2);
    }

    
    /**
     * Determines what determines the precedence of the entered values
     * 
     * @author Bilal Mohamad
     *
     */
    private class NaturalOrder implements Comparator<K> {
    	
        /**
         * Compares two keys and returns a value depending on the order of precedence of the entered keys
         * 
         * @param first		the first key for comparison
         * @param second	the second key for comparison
         * 
         * @return	0	if the keys are the same
         * 			-1	if the first key is before the second key
         * 			1 	the if the second is before the first key
         */
        public int compare(K first, K second) {
            return ((Comparable<K>) first).compareTo(second);
        }
    }
}
