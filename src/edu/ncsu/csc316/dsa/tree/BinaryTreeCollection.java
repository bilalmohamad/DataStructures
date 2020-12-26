package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface used for creating a Binary Tree by extending the BinaryTree interface
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	
	/**
	 * Creates a root for an empty tree, storing value as the element, and returns the position of that root
	 * 
	 * @param value	the value stored at the root
	 * 
	 * @return the position of the root
	 */
    Position<E> addRoot(E value);
    
    /**
     * Creates a left child of position p, storing element e, and returns the position of the new node; an error occurs if p already has a left child.
     * 
     * @param p			the parent node
     * @param value		the value to store at the new node
     * 
     * @return the position of the new node
     */
    Position<E> addLeft(Position<E> p, E value);
    
    /**
     * Creates a right child of position p, storing element e, and returns the position of the new node; an error occurs if p already has a right child.
     * 
     * @param p			the parent node
     * @param value		the value to store at the new node
     * 
     * @return the position of the new node
     */
    Position<E> addRight(Position<E> p, E value);
    
    /**
     * Removes the node at position p, replacing it with its child (if any), and returns the element that had been stored at p; an error occurs if p has two children.
     * 
     * @param p	the node being removed
     * 
     * @return the element that was stored at the removed node
     */
    E remove(Position<E> p);
    
    /**
     * Replaces the element stored at position p with element e, and returns the previously stored element.
     * 
     * @param p		the node to replace the element of
     * @param value	the new value to store in the entered node
     * 
     * @return the previously stored value
     */
    E set(Position<E> p, E value);
}