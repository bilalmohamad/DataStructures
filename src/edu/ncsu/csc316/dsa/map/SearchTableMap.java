package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Creates a sorted map using an array and binary searching
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/** The ArrayBasedList being used to create the map */
    private ArrayBasedList<Entry<K, V>> list;

    
    /**
     * The constructor for creating SearchTableMap objects
     */
    public SearchTableMap() {
        this(null);
    }
    
    
    /**
     * The constructor for creating SearchTableMap objects with a specific comparator
     *
     * @param compare	the comparator to be used
     */
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    
    /**
     * Calls onto the binarySearchHelper method using the entire size of the map as the min and max parameters
     * 
     * @param key	the key used for searching for the map entry
     * 
     * @return	the index of the map entry if it is in the map
     * 			otherwise, a negative index of where the map entry should go to maintain order
     */
    private int lookUp(K key) {
    	return binarySearchHelper(0, list.size() - 1, key);
    }

    
    /**
     * Uses a binary search to find the index of the entered key
     * 
     * @param min	the minimum range value to start from
     * @param max	the maximum range value to end at
     * @param key	the key of the map entry to be searched for
     * 
     * @return	the index of the map entry if it is in the map
     * 			otherwise, a negative index of where the map entry should go to maintain order
     */
    private int binarySearchHelper(int min, int max, K key) {
    	
    	if (max < min) {
    		return -1 * (min + 1);
    	}
    	
    	int mid = (min + max) / 2;
    	
    	int compared = compare(key, list.get(mid).getKey());
    	
    	if (compared == 0) {
    		return mid;
    	}
    	else if (compared < 0) {
    		return binarySearchHelper(min, mid - 1, key);
    	}
    	else {
    		return binarySearchHelper(mid + 1, max, key);
    	}
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public V get(K key) {
        int index = lookUp(key);
        
        if (index < 0) {
        	index = -1 * (index + 1);
        }
        
        if (index >= size() || compare(key, list.get(index).getKey()) != 0) {
        	return null;
        } 
        
        return list.get(index).getValue();
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        for(Entry<K, V> m : list) {
            set.addLast(m);
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        
        if (index < size() && index >= 0 && compare(key, list.get(index).getKey()) == 0) {
        	return list.get(index).setValue(value);
        }
        
        if (index < 0) {
        	index = -1 * (index + 1);
        }
        
    	list.add(index, new MapEntry <K, V>(key, value));
        
        return null;
    }

    @Override
    public V remove(K key) {
        int index = lookUp(key);
        
        if (index < 0 || compare(key, list.get(index).getKey()) != 0) {
        	return null;
        }
        
        return list.remove(index).getValue(); 
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
