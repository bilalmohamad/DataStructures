package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Creates an unordered map using a positional linked list
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** The PositionalList being used to create the map */
    private PositionalList<Entry<K, V>> list;
    
    
    /**
     * The constructor for creating UnorderedLinkedMap objects
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    
    /**
     * Performs a sequential search and returns the position where the entry is located.
     * If the entry is not in the map, return null
     * 
     * @param key	the key used for searching for the map entry
     * 
     * @return	null if the entry is not in the map
     * 			otherwise, the index where the entry is located
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
    	Iterator<Position<Entry<K, V>>> iter = list.positions().iterator();
    	
    	while (iter.hasNext()) {
    		
    		Position<Entry<K, V>> newEntry = iter.next();
    		
			if (newEntry.getElement().getKey().equals(key)) {
				return newEntry;
			}
    	}
    	
    	return null;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if (p == null) {
        	return null;
        }
        
        moveToFront(p);
        return p.getElement().getValue();
    }
    
    
    /**
     * Implements the MTF heuristic for maps to move the entered position to the front of map
     * 
     * @param position	the position to be moved to the front
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	
    	list.remove(position);
    	list.addFirst(position.getElement());
    }

    
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if (p == null) {
        	list.addFirst(new MapEntry<>(key, value));
        	return null;
        }
        
        Entry<K, V> newEntry = p.getElement();
        moveToFront(p);
        V answer = newEntry.setValue(value);
        
        return answer;
    }
    
    
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       
       if (p == null) {
    	   return null;
       }
       
       V answer = list.remove(p).getValue();
       
       return answer;
       
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
        for(Entry<K, V> m : list) {
            set.addLast(m);
        }
        return set;
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
