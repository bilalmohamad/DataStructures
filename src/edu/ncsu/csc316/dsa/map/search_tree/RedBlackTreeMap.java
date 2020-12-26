package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Creates a RedBlack Tree by extending the BinarySearchTreeMap
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * The constructor used for creating RedBlackTreeMap objects
	 */
    public RedBlackTreeMap() {
        super(null);
    }

	/**
	 * The constructor used for creating RedBlackTreeMap objects with a specified comparator
	 * 
	 * @param compare the comparator containing the different type of comparing methods
	 */
    public RedBlackTreeMap(Comparator<K> compare) {
        super(compare);
    }

    /**
     * Helper method to abstract the idea of black
     * 
     * @param p	the position being observed
     * 
     * @return	true	if the position is black
     * 			false	otherwise
     */
    private boolean isBlack(Position<Entry<K, V>> p) {
        return getProperty(p) == 0;
    }

    /**
     * Helper method to abstract the idea of red
     * 
     * @param p	the position being observed
     * 
     * @return	true	if the position is red
     * 			false	otherwise
     */
    private boolean isRed(Position<Entry<K, V>> p) {
        return getProperty(p) == 1;
    }

    /**
     * Set the color for a node to be black
     * 
     * @param p the position being observed
     */
    private void makeBlack(Position<Entry<K, V>> p) {
        setProperty(p, 0);
    }

    /**
     * Set the color for a node to be red
     * 
     * @param p the position being observed
     */
    private void makeRed(Position<Entry<K, V>> p) {
        setProperty(p, 1);
    }

    /**
     * Remedies potential double-red violation above red position p
     * 
     * @param node	the entry being observed
     */
    private void resolveRed(Position<Entry<K, V>> node) {
        
    	/*Position<Entry<K, V>> parent, uncle, middle, grand;
    	parent = parent(node);
    	
    	if (isRed(parent)) {
    		uncle = sibling(parent);
    		
    		if (isBlack(uncle)) {
    			middle = restructure(node);
    			makeBlack(middle);
    			makeRed(left(middle));
    			makeRed(right(middle));
    		}
    		else {
    			makeBlack(parent);
    			makeBlack(uncle);
    			grand = parent(parent);
    			
    			if (!isRoot(grand)) {
    				makeRed(grand);
    				resolveRed(grand);
    			}
    		}
    	}*/
    	
    	
    	Position<Entry<K, V>> current = node;
        Position<Entry<K, V>> parent = parent(node);
        
        if (isRed(parent)) {
        	
        	Position<Entry<K, V>> uncle = sibling(parent);
        	
        	// CASE 1: the uncle (sibling of the parent) is black
        	if (isBlack(uncle)) {
        		Position<Entry<K, V>> middle = restructure(current);
        		
        		makeBlack(middle);
        		makeRed(left(middle));
        		makeRed(right(middle));
        	}
        	// CASE 2: the uncle (sibling of the parent) is red
        	else {
        		makeBlack(parent);
        		makeBlack(uncle);
        		
        		Position<Entry<K, V>> grandparent = parent(parent);
        		
        		if (!isRoot(grandparent)) {
        			makeRed(grandparent);
        			resolveRed(grandparent);
        		}
        	}
        }
    }

    /**
     * Remedies a presumed double-black violation at the given (nonroot) position
     * 
     * @param p	the position being observed
     */
    private void remedyDoubleBlack(Position<Entry<K, V>> p) {
        
    	/*Position<Entry<K, V>> z = parent(p);
    	Position<Entry<K, V>> y = sibling(p);
    	
    	if (isBlack(y)) {
    		if (isRed(left(y)) || isRed(right(y))) {
    			Position<Entry<K,V>> x = (isRed(left(y)) ? left(y) : right(y));
    			Position<Entry<K,V>> middle = restructure(x);
    			if (isRed(z)) {
    				makeRed(middle);
    			}
    			else {
    				makeBlack(middle);
    			}
    			makeBlack(left(middle));
    			makeBlack(right(middle));
    		}
    		else {
    			makeRed(y);
    			
    			if (isRed(z)) {
    				makeBlack(z);
    			}
    			else if (!isRoot(z)) {
    				remedyDoubleBlack(z);
    			}
    		}
    	}
    	else {
    		rotate(y);
    		makeBlack(y);
    		makeRed(z);
    		remedyDoubleBlack(p);
    	}*/
    	
    	Position<Entry<K, V>> node = p;
    	Position<Entry<K, V>> parent = parent(node);
    	Position<Entry<K, V>> sibling = sibling(node);
    	
    	if (isBlack(sibling)) {
    		// CASE 1: trinode restructuring
    		if (isRed(left(sibling)) || isRed(right(sibling))) {
    			Position<Entry<K, V>> temp = (isRed(left(sibling)) ? left(sibling) : right(sibling));
    			Position<Entry<K, V>> middle = restructure(temp);
    			
    			if (isRed(parent)) {
    				makeRed(middle);
    			}
    			else {
    				makeBlack(middle);
    			}
    			
    			makeBlack(left(middle));
    			makeBlack(right(middle));
    		}
    		else {
    			// CASE 2: recoloring
    			makeRed(sibling);
    			
    			if (isRed(parent)) {
    				makeBlack(parent);
    			}
    			else if (!isRoot(parent)) {
    				remedyDoubleBlack(parent);
    			}
    		}
    	}
    	else {
    		// CASE 3: Rotate
    		rotate(sibling);
    		makeBlack(sibling);
    		makeRed(parent);
    		remedyDoubleBlack(node);
    	}
    }

    @Override
    protected void actionOnInsert(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            makeRed(p);
            resolveRed(p);
        }
    }

    @Override
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        if (isRed(p)) {
            makeBlack(p);
        } else if (!isRoot(p)) {
            Position<Entry<K, V>> sib = sibling(p);
            if (isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))) {
                remedyDoubleBlack(p);
            }
        }
    }
}
