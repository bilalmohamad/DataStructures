package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * Creates a tree that allows for traversal runtime similar to a binary search
 * Implements the BinarayTreeCollection interface
 * Extends teh AbstractTree super class
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

    @Override
    public Iterable<Position<E>> inOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();

        if (!isEmpty()) {
        	inOrderHelper(root(), traversal);
        }

        return traversal;
    }

	/**
	 * Helper method used to recursively create the in-order
	 * 
	 * @param p			the current position
	 * @param traversal	the list being traversed
	 */
    private void inOrderHelper(Position<E> p, List<Position<E>> traversal) {
        if (left(p) != null && left(p).getElement() != null) {
        	inOrderHelper(left(p), traversal);
        }
        
        traversal.addLast(p);
        
        if (right(p) != null && right(p).getElement() != null) {
        	inOrderHelper(right(p), traversal);
        }
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        
        if (parent == null){
        	return null;
        }
        
        if (p == left(parent)) {
        	return right(parent);
        }
        else {
        	return left(parent);
        }
    }
    
    private AbstractNode<E> validate(Position<E> p) {
        if(!(p instanceof AbstractNode)) {
            throw new IllegalArgumentException("Position is not a valid binary tree node");
        }
        return (AbstractNode<E>)(p);
    }
    
    @Override
    public int numChildren(Position<E> p) {
        int counter = 0;
        
        if (left(p) != null) {
        	counter++;
        }
        if (right(p) != null) {
        	counter++;
        }
        
        return counter;
    }
    
    @Override
    public E set(Position<E> p, E value) {
        AbstractNode<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(inOrder().iterator());
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractNode<E> node = validate(p);
        List<Position<E>> returnList = new SinglyLinkedList<Position<E>>();
        if(left(node) != null) {
            returnList.addLast(left(node));
        }
        if(right(node) != null) {
            returnList.addLast(right(node));
        }
        return returnList;
    }
}
