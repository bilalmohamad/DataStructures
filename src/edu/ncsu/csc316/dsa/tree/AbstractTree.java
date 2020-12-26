package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * Creates an abstract super class to be extending by any classes creating an Abstract Tree
 * Implements the Tree interface
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    // Since every tree node is going to be a "Position", every tree
    // node will need the getElement and setElement methods
    /**
     * Creates the individual nodes of the AbstractTree
     * 
	 * @author Bilal Mohamad
	 *
	 * @param <E> The object type
	 */
    protected abstract static class AbstractNode<E> implements Position<E> {

    	/** The value of the node */
        private E element;
        
        /**
         * The constructor used for creating an AbstractNode
         * 
         * @param element the value for the AbstractNode
         */
        public AbstractNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the AbstractNode value to the entered value
         * 
         * @param element the new value for the AbstractNode
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
	@Override
    public Iterable<Position<E>> preOrder()
    {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }

	/**
	 * Helper method used to recursively create the pre-order
	 * 
	 * @param p			the current position
	 * @param traversal	the list being traversed
	 */
    private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
        traversal.addLast(p);
        for(Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }
	
    @Override
    public Iterable<Position<E>> postOrder() {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
            postOrderHelper(root(), list);
        }
        return list;
    }
    
	/**
	 * Helper method used to recursively create the post-order
	 * 
	 * @param p			the current position
	 * @param traversal	the list being traversed
	 */
    private void postOrderHelper(Position<E> p, List<Position<E>> list) {
        for(Position<E> c : children(p)) {
            postOrderHelper(c, list);
        }
        list.addLast(p);
    }

    
    //TODO FIGURE OUT METHOD
	@Override
	public Iterable<Position<E>> levelOrder() {
		
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		Queue<Position<E>> queue = new ArrayBasedQueue<Position<E>>();
		
		if (isEmpty()) {
			return list;
		}
		
		queue.enqueue(root());
		
		while (!queue.isEmpty()) {
			Position<E> parent = queue.dequeue();
			list.addLast(parent);
			
			for (Position<E> child : children(parent)) {
				queue.enqueue(child);
			}
		}
		
		return list;
	}
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Helper method used for converting the tree into a string
     * 
     * @param sb		the main String 
     * @param indent	the indentation to be used
     * @param root		position of the root of the tree
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }    
    

    /**
     * Iterator used for iterating through all the elements of the tree
     * 
     * @author Bilal Mohamad
     *
     */
    protected class ElementIterator implements Iterator<E> {
    	
    	/** An iterator of the positions of the tree */
        private Iterator<Position<E>> it;
        
        /**
         * Constructor used for creating the ElementIterator
         * 
         * @param iterator the iterator of the positions
         */
        public ElementIterator(Iterator<Position<E>> iterator) {
            it = iterator;
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
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    } 
}
