package edu.ncsu.csc316.dsa.stack;

/**
 * The abstract class that implements the Stack interface containing implementation of the 
 * isEmpty method.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type of the list.
 */
public abstract class AbstractStack<E> implements Stack<E> {
	
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
