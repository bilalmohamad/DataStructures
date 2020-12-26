package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * The abstract parent class used for creating Hash Maps
 * Extends the AbstractMap for basic map functionality
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /** An initial capacity for the hash table */
    protected static final int DEFAULT_CAPACITY = 17;
    
    /**
     * From our discussion in class, the expected number of probes
     * for separate chaining remains relatively the same no matter
     * what the load factor may be. However, for linear probing, to
     * reduce the chance of having large clusters, we will resize
     * when the load factor reaches 0.5
     */
    private static final double MAX_LOAD_FACTOR = 0.5;
    
    /** Define a default prime number for our compression strategy */
    protected static final int DEFAULT_PRIME = 109345121;
    
    // Alpha and Beta values for MAD compression
    // This implementation uses a variation of the MAD method
    // where h(k) = ( (alpha * f(k) + beta) % DEFAULT_PRIME) % capacity
    /** Used for MAD compression */
    private long alpha;
    /** Used for MAD compression */
    private long beta;
    
    /**
     * The constructor used for creating AbstractHashMap objects with a specified capacity
     * 
     * @param capacity	the capacity of the AbstractHashMap
     */
    public AbstractHashMap(int capacity) {
        Random rand = new Random();
        // Generate a random alpha value (cannot be 0)
        alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
        // Generate a random beta value
        beta = rand.nextInt(DEFAULT_PRIME);
        createTable(capacity);
    }
    
    
    /**
     * The constructor used for creating AbstractHashMap objects without a specified capacity
     */
    public AbstractHashMap() {
        this(DEFAULT_CAPACITY);
    }
    
    
    /**
     * Compress the bucket containing the specified key
     * 
     * @param key	the key being compressed
     * 
     * @return	the number resulting from the made calculation due to compression
     */
    private int compress(K key) {
        return (int)((Math.abs(key.hashCode() * alpha + beta) % DEFAULT_PRIME) % capacity());
    }

    
    @Override
    public V put(K key, V value) {
        V ret = bucketPut(compress(key), key, value);
        if( (double)size() / capacity() > MAX_LOAD_FACTOR){
            resize(2 * capacity() + 1);
        }
        return ret;
    }
    
    
    @Override
    public V get(K key) {
        return bucketGet(compress(key), key);
    }

    
    @Override
    public V remove(K key) {
        return bucketRemove(compress(key), key);
    }
    
    
    /**
     * Resizes the map so that it can contain more entries
     * 
     * @param newCapacity the new capacity to use for the map
     */
    private void resize(int newCapacity) {
        List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for(Entry<K, V> entry : entrySet()) {
            list.addLast(entry);
        }
        createTable(newCapacity);
        for(Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Retrieves the capacity of the hash map
     * 
     * @return	the capacity of the hash map
     */
    protected abstract int capacity();
    
    /**
     * Creates a table with the specified capacity
     * 
     * @param capacity the capacity of the table being made
     */
    protected abstract void createTable(int capacity);
    
    /**
     * Retrieves the value stored at the entered hash and key
     * 
     * @param hash	the hash being observed
     * @param key	the key being observed
     * 
     * @return the value stored at the entered hash and key
     * 			otherwise null if location is empty
     */
    protected abstract V bucketGet(int hash, K key);
    
    /**
     * Puts the entry at the entered hash and key. 
     * If the hash and key contain a value, then that value is replaced with the entered value.
     * 
     * @param hash	the hash being observed
     * @param key	the key being observed
     * @param value	the value to go with the hash and key
     * 
     * @return	if the location has a value stored, the value previously stored at that location otherwise
     * 			null if the hash and key location is empty
     * 			
     */
    protected abstract V bucketPut(int hash, K key, V value);
    
    /**
     * Removes the key and value stored at the specified hash and key
     * 
     * @param hash	the hash being observed
     * @param key	the key being removed
     * 
     * @return	null if the location is empty otherwise
     * 			the value associated with the key that was removed
     */
    protected abstract V bucketRemove(int hash, K key);
}