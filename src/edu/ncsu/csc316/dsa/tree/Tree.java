package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface used to create a tree
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public interface Tree<E> {
	
	/**
	 * Retrieves the position of the root
	 * 
	 * @return the position of the root
	 */
    Position<E> root();
    
    /**
     * Retrieves the parent of the entered position
     * 
     * @param p	the position being observed
     * 
     * @return the position of the parent of the entered position
     */
    Position<E> parent(Position<E> p);
    
    /**
     * Creates an Iterable of the children of the entered position
     * 
     * @param p	the position being observed
     * 
     * @return an Iterable of the children of the entered position
     */
    Iterable<Position<E>> children(Position<E> p);
    
    /**
     * Returns the number of children to the entered position
     * 
     * @param p	the position being observed
     * 
     * @return the number of children of the entered position
     */
    int numChildren(Position<E> p);
    
    /**
     * Determines if the entered position is internal of the tree
     * 
     * @param p	the position being observed
     * 
     * @return	true	if the position is internal
     * 			false	otherwise
     */
    boolean isInternal(Position<E> p);
    
    /**
     * Determines if the entered position is a leaf of the tree
     * 
     * @param p	the position being observed
     * 
     * @return	true	if the position is a leaf
     * 			false	otherwise
     */
    boolean isLeaf(Position<E> p);
    
    /**
     * Determines if the entered position is a root of the tree
     * 
     * @param p	the position being observed
     * 
     * @return	true	if the position is a root
     * 			false	otherwise
     */
    boolean isRoot(Position<E> p);
    
    /**
     * Returns the size of the tree
     * 
     * @return the size of the tree
     */
    int size();
    
    /**
     * Determines if the tree is empty
     * 
     * @return	true	if the tree is empty
     * 			false	otherwise
     */
    boolean isEmpty();
    
    // We will also add the following traversals to our Tree interface
    /**
     * Creates a pre-order Iterable of the tree
     * 
     * @return a pre-order Iterable of the tree
     */
    Iterable<Position<E>> preOrder();
    
    /**
     * Creates a post-order Iterable of the tree
     * 
     * @return a post-order Iterable of the tree
     */
    Iterable<Position<E>> postOrder();
    
    /**
     * Creates a level-order Iterable of the tree
     * 
     * @return a level-order Iterable of the tree
     */
    Iterable<Position<E>> levelOrder();
}
