package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;

/**
 * Creates a hash map using the Separate Chaining strategy
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	/** An array of map entries */
    private Map<K, V>[] table;
    
    /** The size of the hash map */
    private int size;
    
    
    /**
     * The constructor used for creating SeparateChainingHashMap objects without a specified capacity
     */
    public SeparateChainingHashMap() {
        super();
    }
    
    
    /**
     * The constructor used for creating SeparateChainingHashMap objects with a specified capacity
     * 
     * @param capacity	the capacity of the SeparateChainingHashMap
     */
    public SeparateChainingHashMap(int capacity) {
        super(capacity);
        size = 0;
    }

    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
        for(int i = 0; i < capacity(); i++) {
            if(table[i] != null) {
                // Each bucket contains a map, so include
                // all entries in the entrySet for the map
                // at the current bucket
                for(Entry<K, V> entry : table[i].entrySet()) {
                    list.addLast(entry);
                }
            }
        }
        return list;
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // Example -- change this to whatever map you'd like
        table = (BinarySearchTreeMap<K, V>[])new BinarySearchTreeMap[capacity];
        size = 0;
    }

    
    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist
        if (bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value
        return bucket.get(key);
    }

    
    @Override
    public V bucketPut(int hash, K key, V value) {
        Map<K, V> bucket = table[hash];
        
        if (bucket == null) {
        	bucket = new BinarySearchTreeMap<>();
        	table[hash] = bucket;
        }
        
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        size += (bucket.size() - oldSize);
        
        return answer;
    }

    
    @Override
    public V bucketRemove(int hash, K key) {
        Map<K, V> bucket = table[hash];
        
        if (bucket == null) {
        	return null;
        }
        
        int oldSize = bucket.size();
        V answer = bucket.remove(key);
        size -= (oldSize - bucket.size());
        
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
}
