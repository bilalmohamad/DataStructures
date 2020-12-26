package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * Abstract class used for implementation of AbstractMaps
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * Used for accessing the MapEntry from the subclasses
	 * 
	 * @author Bilal Mohamad
	 *
     * @param <K>	the type of the key
     * @param <V>	the type of the value
	 */
    protected static class MapEntry<K, V> implements Entry<K, V> {

    	/** The key of the map entry */
        private K key;
        
        /** The value of the map entry */
        private V value;

        /**
         * The constructor used for making MapEntry objects
         * 
         * @param key	the key of the MapEntry
         * @param value	the value of the MapEntry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the current key to the entered key
         * 
         * @param key the key to be set to
         * 
         * @return the key that was previously at the current key
         */
        public K setKey(K key) {
            this.key = key;
            return this.key;
        }

        @Override
        public V setValue(V value) {
            V original = this.value;
            this.value = value;
            return original;
        }
    }

    /**
     * Allows for iteration through the keys of the map
     * 
     * @author Bilal Mohamad
     *
     */
    protected class KeyIterator implements Iterator<K> {

    	/** The current iterator for the entries */
        private Iterator<Entry<K, V>> it;
        
        /**
         * The constructor used to create KeyIterator objects
         * 
         * @param iterator	the iterator containing the information to be iterated
         */
        public KeyIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public K next() {
            return it.next().getKey();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }
    
    /**
     * Allows for iteration through the values of the map
     * 
     * @author Bilal Mohamad
     *
     */
    protected class ValueIterator implements Iterator<V> {
    	
    	/** The current iterator for the entries */
    	private Iterator<Entry<K, V>> it;
    	
        /**
         * The constructor used to create ValueIterator objects
         * 
         * @param iterator	the iterator containing the information to be iterated
         */
    	public ValueIterator(Iterator<Entry<K, V>> iterator) {
    		it = iterator;
    	}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public V next() {
			return it.next().getValue();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator(entrySet().iterator());
    }
    
    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
    
    /**
     * Used to retrieve the iterable of the current ValueIterator
     * 
     * @author Bilal Mohamad
     *
     */
    private class ValueIterable implements Iterable<V> {
    	
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator(entrySet().iterator());
        }
    }
    
}
