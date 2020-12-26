package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class creates a PositionalLinkedList through the implementation of the PositionalList interface. 
 * Several methods and information were retrieved from the textbook pages 274-280 and page 287.
 * 
 * @author Bilal Mohamad 
 *
 * @param <E> The object type of the list.
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** The PositionalNode at the front of the list */
    private PositionalNode<E> front;
    
    /** The PositionalNode at the end of the list */
    private PositionalNode<E> tail;
    
    /** The size of the list */
    private int size;

    
    /**
     * The constructor used for creating PositionalLinkedList objects
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }
    
    
    /**
     * Adds a value between two entered positions
     * 
     * @param value	the value being added
     * @param next	the following position
     * @param prev	the previous position
     * 
     * @return	the position the element was added to
     */
    private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newest = new PositionalNode<E>(value, next, prev);
        prev.setNext(newest);
        next.setPrevious(newest);
        size++;
    	return newest;

    }
    
    
    @Override
	public int size() {
		return size;
	}


    @Override
    public Iterator<E> iterator() {
        // we start at front.getNext() because front is a dummy/sentinel node
        return new ElementIterator(front.getNext());
    }


	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}


	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		
		if (node.getNext().getElement() == null) {
			throw new NoSuchElementException();
		}
		
		return node.getNext();
	}


	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		
		if (node.getPrevious().getElement() == null) {
			throw new NoSuchElementException();
		}
		
		return node.getPrevious();
	}


	@Override
	public Position<E> first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.getNext();
	}


	@Override
	public Position<E> last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.getPrevious();
	}


	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		PositionalNode<E> node = validate(p);
		return addBetween(value, node.getNext(), node);
	}


	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		PositionalNode<E> node = validate(p);
		return addBetween(value, node, node.getPrevious());
	}


	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.getNext(), front);
	}


	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.getPrevious());
	}


	@Override
	public boolean isEmpty() {
		return size == 0;
	}


	@Override
	public E remove(Position<E> p) {
		
		PositionalNode<E> node = validate(p);
		PositionalNode<E> predecessor = node.getPrevious();
		PositionalNode<E> successor = node.getNext();
		
		predecessor.setNext(successor);
		successor.setPrevious(predecessor);
		size--;
		
		E answer = p.getElement();
		return answer;
	}


	@Override
	public E set(Position<E> p, E value) {
		PositionalNode<E> node = validate(p);
		E answer = p.getElement();
		node.setElement(value);
		return answer;
	}


	/**
	 * Ensures the position is valid and will not cause errors.
	 * 
	 * @param p	the position being observed
	 * 
	 * @return the PositionalNode of the entered position
	 * 
	 * @throws IllegalArgumentException if the position is invalid
	 */
	private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	
	/**
	 * Retrieves the position of the entered PositionalNode
	 * 
	 * @param node	the PositionalNode
	 * 
	 * @return the position of the PositionalNode
	 */
	/*private Position<E> position(PositionalNode<E> node) {
		if (node == front || node == tail) {
			return null;
		}
		return node;
	}*/
    
    
    
	/**
	 * The individual nodes that the PositionalLinkedList is comprised of
	 * 
	 * @author Bilal Mohamad 
	 *
	 * @param <E> The object type of the list.
	 */
    private static class PositionalNode<E> implements Position<E> {

    	/** The element stored in the PositionalNode */
        private E element;
        
        /** The following PositionalNode */
        private PositionalNode<E> next;
        
        /** The previous PositionalNode */
        private PositionalNode<E> previous;

        
        /**
         * Used to construct a PositionalNode object
         * 
         * @param element	the element to be stored in the PositionalNode
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        
        /**
         * Used to construct a PositionalNode object with a following PositionalNode
         * 
         * @param element	the element to be stored in the PositionalNode
         * @param next		the following PositionalNode
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        
        /**
         * Used to construct a PositionalNode object with a following and previous PositionalNode
         * 
         * @param element	the element to be stored in the PositionalNode
         * @param next		the following PositionalNode
         * @param prev		the previous PositionalNode
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        
        /**
         * Sets the previous PositionalNode to the entered PositionalNode
         * 
         * @param prev	the entered PositionalNode
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        
        /**
         * Retrieves the previous PositionalNode
         * 
         * @return the previous PositionalNode
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        
        /**
         * Sets the next PositionalNode to the entered PositionalNode
         * 
         * @param prev	the entered PositionalNode
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        
        /**
         * Retrieves the next PositionalNode
         * 
         * @return the next PositionalNode
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        
        @Override
        public E getElement() {
            return element;
        }
        
        
        /**
         * Sets the element at the current PositionalNode
         * 
         * @param element the element to set to
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    
    
    /**
     * This inner class contains the functionality of the Iterator for the PositionalLinkedList
     * 
     * @author Bilal Mohamad
     *
     */
    private class PositionIterator implements Iterator<Position<E>> {

    	/** The current position being observed */
        private Position<E> current;
        
        /** The indicator used to determine whether it is ok to remove */
        private boolean removeOK;

        
        /**
         * The constructor used for creating PositionIterator objects
         * 
         * @param start the position to start from
         */
        public PositionIterator(PositionalNode<E> start) {
            this.current = start;
            removeOK = false;
        }

        
        @Override
        public boolean hasNext() {
        	PositionalNode<E> node = validate(current);
            removeOK = false;
            
            boolean answer = node.getElement() != null;
            return answer;
        }

        
        @Override
        public Position<E> next() {
        	removeOK = true;
        	
        	PositionalNode<E> node = validate(current);
            current = node.getNext();
            return node;
        }

        
        @Override
        public void remove() {
        	if (removeOK) {
        		
        		PositionalNode<E> node = validate(current);
        		PositionalNode<E> previous = node.getPrevious();
        		PositionalLinkedList.this.remove(previous);
        		
        		removeOK = false;
        	}
        	else {
        		throw new IllegalStateException();
        	}
        }
    }
    
    
    
    /**
     * The iterator used for traversing the elements in the list
     * 
     * @author Bilal Mohamad
     *
     */
    private class ElementIterator implements Iterator<E> {

    	/** The Iterator used to tranverse between positions */
        private Iterator<Position<E>> it;

        
    	/**
    	 * Used to construct ElementIterator objects using a starting PositionalNode
    	 * 
    	 * @param start the starting point of the iterator
    	 */
        public ElementIterator(PositionalNode<E> start) {
            it = new PositionIterator(start);
        }

        
        @Override
        public boolean hasNext() {
        	return it.hasNext();
        }

        
        @Override
        public E next() {
        	return it.next().getElement();
        }

        
        @Override
        public void remove() {
        	it.remove();
        }
    }
    
    
    
    /**
     * This inner class is used to retrieve the Iterator with the current position
     * 
     * @author Bilal Mohamad
     *
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator(front.getNext());
        }
    }
}
