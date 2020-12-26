package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface containing the basic functions required to make a Binary Tree
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public interface BinaryTree<E> extends Tree<E> {
	
	/**
	 * Returns the position of the left child of p (or null if p has no left child).
	 * 
     * @param p the node being observed
     * 
     * @return the position of the left child or null if p has no left child
     */
    Position<E> left(Position<E> p);
    
	/**
	 * Returns the position of the right child of p (or null if p has no right child).
	 * 
     * @param p the node being observed
     * 
     * @return the position of the right child or null if p has no right child
     */
    Position<E> right(Position<E> p);
    
    /**
     * Returns the position of the sibling of p (or null if p has no sibling).
     * 
     * @param p the node being observed
     * 
     * @return the position of the sibling of p or null if p has no sibling
     */
    Position<E> sibling(Position<E> p);
    
    /**
     * Returns an Iterable of all the nodes in order as they appear in the tree
     * 
     * @return	an Iterable of all the nodes in order as they appear in the tree 
     */
    Iterable<Position<E>> inOrder();
}