package edu.ncsu.csc316.dsa.queue;

/**
 * Abstract class used create queues
 * Implements the Queue interface
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type of the list.
 */
public abstract class AbstractQueue<E> implements Queue<E> {
	
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}