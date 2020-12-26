package edu.ncsu.csc316.dsa.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Creates a Queue using an array.
 * Extends the AbstractQueue class
 * 
 * @author Bilal Mohamad 
 *
 * @param <E> The object type of the list.
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/** The array containing the data of the list */
    private E[] data;
    
    /** The index of the front of the list */
    private int front;
    
    /** The index after the last element in the list */
    private int rear;
    
    /** The size of the list */
    private int size;
    
    /** The default list capacity in case a capacity is not specified */
    private static final int DEFAULT_CAPACITY = 10;
    
    
    /**
     * Creates an ArrayBased Queue object with the entered capacity
     * 
     * @param initialCapacity the capacity of the ArrayBasedQueue
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity)
    {
        data = (E[])(new Object[initialCapacity]);
        size = 0;
    }
    

    /**
     * Creates an ArrayBased Queue object with the default capacity
     */
    public ArrayBasedQueue()
    {
        this(DEFAULT_CAPACITY);
    }


	/**
	 * Ensures the capacity of the array is valid
	 * 
	 * @param minCapacity	the minimum capacity of the array
	 */
    private void ensureCapacity(int minCapacity) {
	    int oldCapacity = data.length;
	    if (minCapacity > oldCapacity) {
	        int newCapacity = (oldCapacity * 2) + 1;
	        if (newCapacity < minCapacity) {
	            newCapacity = minCapacity;
	        }
	        data = Arrays.copyOf(data, newCapacity);
	    }
    }
    
    
	@Override
	public void enqueue(E value) {
		
		ensureCapacity(size);
		/*if (size == data.length) {
			throw new IllegalArgumentException();
		}
		int index = (front + size) % data.length;
		data[index] = value;
		size++;*/
		data[rear] = value;
		size++;
		rear = (front + size) % data.length;
	}


	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E answer = data[front];
//		data[front] = null;
		front = (front + 1) % data.length;
		size--;
		
		return answer;
	}


	@Override
	public E front() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[front];
	}


	@Override
	public int size() {
		return size;
	}
}
