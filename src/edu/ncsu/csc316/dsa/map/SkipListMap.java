package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Creates a sorted map using an array and a skip list, which includes quad nodes
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/** Simulates tossing a coin where there is a 50/50 chance of each outcome */
    private Random coinToss;
    
    /** The starting point of the map */
    private SkipListEntry<K, V> start;
    
    /** The size of the map */
    private int size;
    
    /** The height of the map*/
    private int height;

    
    /**
     * The constructor for creating SkipListMap objects
     */
    public SkipListMap() {
        this(null);
    }

    
    /**
     * The constructor for creating SkipListMap objects with a specific comparator
     *
     * @param compare	the comparator to be used
     */
    public SkipListMap(Comparator<K> compare) {
         super(compare);
         coinToss = new Random();        
         // Create a dummy node for the left "-INFINITY" sentinel tower
         start = new SkipListEntry<K, V>(null, null);
         // Create a dummy node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListEntry<K, V>(null, null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
        coinToss = new Random();
    }
    
    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    /**
     * Checks if the entered map entry is a sentinel node (-INFINITY or +INFINITY nodes containing a null key)
     * 
     * @param entry	the entry to be checked
     * 
     * @return 	true	if the entered entry contains a sentinel node
     * 			false	if the entered entry does not contain a sentinel node
     */
    private boolean isSentinel(SkipListEntry<K, V> entry) {
        return entry.getKey() == null;
    }

    
    /**
     * Searches the map for map entry containing the key
     * 
     * @param key	the key to search for
     * 
     * @return	the starting entry if the key could not be found
     * 			otherwise, the map entry containing the entered key
     */
    private SkipListEntry<K, V> lookUp(K key) {
    	
        SkipListEntry<K, V> current = start;
        while (current.getBelow() != null) {
            current = current.getBelow();
            while (!isSentinel(current.getNext()) && compare(key, current.getNext().getKey()) >= 0) {
                current = current.getNext();
            }
        }
        return current;
    }

    @Override
    public V get(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        
        if(temp.getKey() != key) {
        	return null;
        }
        
        return temp.getValue();
    }

    
    /**
     * Inserts a map entry after the above node
     * 
     * @param prev	the previous map entry
     * @param down	the map entry below the current entry
     * @param key	the key of the new map entry
     * @param value	the value of the new map entry
     * 
     * @return	the newly created map entry
     */
    private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
    	SkipListEntry<K, V> newEntry = new SkipListEntry<K, V>(key, value);
    	
    	newEntry.setBelow(down);
    	newEntry.setPrevious(prev);
    	
    	if (newEntry.getPrevious() != null) {
    		newEntry.setNext(prev.getNext());
    		newEntry.getPrevious().setNext(newEntry);
    	}
    	
    	if (newEntry.getNext() != null) {
    		newEntry.getNext().setPrevious(newEntry);
    	}
    	
    	if (down != null) {
    		down.setAbove(newEntry);
    	}
    	
    	return newEntry;
    }
    
    @Override
    public V put(K key, V value) {
      //SkipListEntry<K, V> temp = lookUp(key);
      SkipListEntry<K, V> p = lookUp(key);
      
      if (p.getKey() == key) {
    	  V originalValue = p.getValue();
    	  
    	  while (p != null) {
    		  p.setValue(value);
    		  p = p.getAbove();
    	  }
    	  
    	  return originalValue;
      }
      
      SkipListEntry<K, V> q = null;
      int currentLevel = -1;
      
      do {
    	  currentLevel = currentLevel + 1;

    	  if (currentLevel >= height) {
    		  
    		  height = height + 1;
    		  SkipListEntry<K, V> tail = start.getNext();
    		  
    		  start = insertAfterAbove(null, start, null, null);
    		  insertAfterAbove(start, tail, null, null);
    	  }
    	  
    	  q = insertAfterAbove(p, q, key, value);
    	  
    	  while (p.getAbove() == null) {
    		  p = p.getPrevious();
    	  }
    	  
    	  p = p.getAbove();
      } while (coinToss.nextBoolean());
      
      size++;
      return null;

    }

    @Override
    public V remove(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        
        if (temp.getKey() != key) {
        	return null;
        }
        
        V answer = temp.getValue();
        
        while (temp != null) {
        	temp.prev.next = temp.next;
        	temp.next.prev = temp.prev;
        	temp = temp.above;
        }
        
        size--;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        SkipListEntry<K, V> current = start;
        while(current.below != null){
            current = current.below;
        }
        current = current.next;
        while(!isSentinel(current)) {
            set.addLast(current);
            current = current.next;
        }
        return set;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
        SkipListEntry<K, V> cursor = start;
        while( cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while(cursor != null && cursor.getKey() != null) {
            sb.append(cursor.getKey());
            if(cursor.next != null && cursor.next.getKey() != null) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");
        
        return sb.toString();
    }
    
    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
    /**
     * This method is used for testing the map
     * 
     * @return a String form of the map
     */
    public String toFullString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        SkipListEntry<K, V> cursor = start;
        SkipListEntry<K, V> firstInList = start;
        while( cursor != null) {
            firstInList = cursor;
            sb.append("-INF -> ");
            cursor = cursor.next;
            while(cursor != null && !isSentinel(cursor)) {
                sb.append(cursor.getKey() + " -> ");
                cursor = cursor.next;
            }
            sb.append("+INF\n");
            cursor = firstInList.below;
        }
        sb.append("]");
        return sb.toString();
    }
    
    
    
    /**
     * Creates a custom map entry to be used for the SkipListMap
     * 
	 * @author Bilal Mohamad
	 *
	 * @param <K>	the type of the key
	 * @param <V>	the type of the value
	 */
    private static class SkipListEntry<K, V> extends MapEntry<K, V> {

    	/** The map entry above the current map entry */
        private SkipListEntry<K, V> above;
        
        /** The map entry below the current map entry */
        private SkipListEntry<K, V> below;
        
        /** The map entry previous of the current map entry */
        private SkipListEntry<K, V> prev;
        
        /** The map entry next of the current map entry */
        private SkipListEntry<K, V> next;

        
        /**
         * The constructor for creating SkipListEntry objects
         * 
         * @param key	the key of the entry
         * @param value	the value of the entry
         */
        public SkipListEntry(K key, V value) {
            super(key, value);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        
        /**
         * Retrieves the map entry below the current map entry
         * 
         * @return the map entry below the current map entry
         */
        public SkipListEntry<K, V> getBelow() {
            return below;
        }
        
        
        /**
         * Retrieves the map entry after the current map entry
         * 
         * @return the map entry after the current map entry
         */
        public SkipListEntry<K, V> getNext() {
            return next;
        }
        
        
        /**
         * Retrieves the map entry before the current map entry
         * 
         * @return the map entry before the current map entry
         */
        public SkipListEntry<K, V> getPrevious() {
            return prev;
        }
        
        
        /**
         * Retrieves the map entry above the current map entry
         * 
         * @return the map entry above the current map entry
         */
        public SkipListEntry<K, V> getAbove() {
            return above;
        }
        
        
        /**
         * Sets the entry below the current map entry to the entered entry
         * 
         * @param down	the entry to set the entry below the current map entry to
         */
        public void setBelow(SkipListEntry<K, V> down) {
            this.below = down;
        }
        
        
        /**
         * Sets the entry after the current map entry to the entered entry
         * 
         * @param next	the entry to set the entry after the current map entry to
         */
        public void setNext(SkipListEntry<K, V> next) {
            this.next = next;
        }
        
        
        /**
         * Sets the entry before the current map entry to the entered entry
         * 
         * @param prev	the entry to set the entry before the current map entry to
         */
        public void setPrevious(SkipListEntry<K, V> prev) {
            this.prev = prev;
        }
        
        
        /**
         * Sets the entry above the current map entry to the entered entry
         * 
         * @param up	the entry to set the entry above the current map entry to
         */
        public void setAbove(SkipListEntry<K, V> up) {
            this.above = up;
        }
    }
}