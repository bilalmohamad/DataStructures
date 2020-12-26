package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Creates an unordered map using an array
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	/** The ArrayBasedList being used to create the map */
    private ArrayBasedList<Entry<K, V>> list;
    
    
    /**
     * The constructor for creating UnorderedArrayMap objects
     */
    public UnorderedArrayMap() {
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * Performs a sequential search and returns the index where the entry is located.
     * If the entry is not in the map, return -1
     * 
     * @param key	the key used for searching for the map entry
     * 
     * @return	-1 if the entry is not in the map
     * 			otherwise, the index where the entry is located
     */
    private int lookUp(K key)
    {
    	Iterator<Entry<K, V>> iter = list.iterator();
    	int index = -1;
    	
    	while (iter.hasNext()) {
    		
    		index++;
    		Entry<K, V> newEntry = iter.next();
    		
    		if (newEntry.getKey().equals(key)) {
    			return index;
    		}
    	}
    	
    	return -1;
    }
    
    @Override
    public V get(K key) {
        int index = lookUp(key);
        
        if (index == -1) {
        	return null;
        }
        return transpose(index);
    }
    
    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        
        if (index == -1) {
        	list.addFirst(new MapEntry<>(key, value));
        	return null;
        }
        else {
        	Entry<K, V> newEntry = list.get(index);
        	transpose(index);
        	return newEntry.setValue(value);
        }
    }
    
    @Override
    public V remove(K key) {
       int index = lookUp(key);
       
       if (index == -1) {
    	   return null;
       }
       
       V answer = list.remove(index).getValue();
       
       return answer;
    }

    @Override
    public int size() {
        return list.size();
    }
    
    private V transpose(int index)
    {
        V answer = list.get(index).getValue();
        
        if (index == 0) {
        	return answer;
        }
        
        Entry<K, V> previous = list.remove(index - 1);
        list.add(index, previous);
        
        return answer;
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {

    	ArrayBasedList<Entry<K, V>> buffer = new ArrayBasedList<Entry<K, V>>();
    	Iterator<Entry<K, V>> iter = list.iterator();
    	
    	while (iter.hasNext()) {
    		buffer.addLast(iter.next());
    	}
    	
    	return buffer;
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
