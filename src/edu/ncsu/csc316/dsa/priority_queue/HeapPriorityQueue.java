package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;


/**
 * Creates a PriorityQueue using the heap strategy
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K> the key
 * @param <V> the value
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    /** The list containing the information of the heap */
    protected ArrayBasedList<Entry<K, V>> list;

    
    /**
     * The constructor used for creating HeapPriorityQueue objects with a comparator
     * 
     * @param comparator the comparator to be used
     */
    public HeapPriorityQueue(Comparator<K> comparator) {
        super(comparator);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    
    /**
     * The constructor used for creating HeapPriorityQueue objects without a comparator
     */
    public HeapPriorityQueue() {
        this(null);
    }
    
    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in jumping to parent or children
    //////////////////////////////////////////////////
    
    /**
     * Retrieves the parent of the entered index
     * 
     * @param index the index being observed
     * 
     * @return the index of the parent
     */
    protected int parent(int index) {
        return (index - 1) / 2;
    }

    
    /**
     * Retrieves the index left of the entered index
     * 
     * @param index the index being observed
     * 
     * @return the index to the left
     */
    protected int left(int index) {
        return 2 * index + 1;
    }

    
    /**
     * Retrieves the index right of the entered index
     * 
     * @param index the index being observed
     * 
     * @return the index to the right
     */
    protected int right(int index) {
        return 2 * index + 2;
    }

    
    /**
     * Checks if the index has a left
     * 
     * @param index the index being observed
     * 
     * @return	true	if there is a left index
     * 			false	otherwise
     */
    protected boolean hasLeft(int index) {
        return left(index) < list.size();
    }

    
    /**
     * Checks if the index has a right
     * 
     * @param index the index being observed
     * 
     * @return	true	if there is a right index
     * 			false	otherwise
     */
    protected boolean hasRight(int index) {
        return right(index) < list.size();
    }

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////
    
    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> temp = createEntry(key, value);
        
        list.addLast(temp);
        upHeap(list.size() - 1);
        return temp;        
    }

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }

    @Override
    public Entry<K, V> deleteMin() {
        if (list.isEmpty()) {
            return null;
        }
        
        Entry<K, V> answer = list.get(0);
        swap(0, list.size() - 1);
        list.remove(list.size() - 1);
        
        downHeap(0);
        return answer;
    }

    @Override
    public int size() {
        return list.size();
    }

    //////////////////////////////////////////////
    // Bubbling Operations (up heap, down heap)
    //////////////////////////////////////////////
    
    
    /**
     * Traverses the element up the heap
     * 
     * @param index the index being observed
     */
    protected void upHeap(int index) {
        // TODO your code here
        // The textbook has a non-recursive version of 
        //    the recursive algorithms presented in the lecture slides
    	while (index > 0) {
    		int p = parent(index);
    		if (compare(list.get(index).getKey(), list.get(p).getKey()) >= 0) {
    			break;
    		}
    		swap(index, p);
    		index = p;
    	}
    }

    
    /**
     * Traverses the element down the heap
     * 
     * @param index the index being observed
     */
    protected void downHeap(int index) {
        // TODO your code here
        // The textbook has a non-recursive version of
        //    the recursive algorithms presented in the lecture slides
    	while (hasLeft(index)) {
    		int leftIndex = left(index);
    		int smallChildIndex = leftIndex;
    		
    		if (hasRight(index)) {
    			int rightIndex = right(index);
    			
    			if (compare(list.get(leftIndex).getKey(), list.get(rightIndex).getKey()) > 0) {
    				smallChildIndex = rightIndex;
    			}
    		}
    		
    		if (compare(list.get(smallChildIndex).getKey(), list.get(index).getKey()) >= 0) {
    			break;
    		}
    		
    		swap(index, smallChildIndex);
    		index = smallChildIndex;
    	}
    }
    
    
    /**
     * Swaps the two indexes
     * 
     * @param index1 the first index
     * @param index2 the second index
     */
    protected void swap(int index1, int index2) {
        Entry<K, V> temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}