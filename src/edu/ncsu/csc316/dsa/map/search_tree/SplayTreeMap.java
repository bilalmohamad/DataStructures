package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Creates a Splay Tree by extending the BinarySearchTreeMap
 * 
 * @author Bilal Mohamad
 *
 * @param <K>	the type of the key
 * @param <V>	the type of the value
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * The constructor used for creating SplayTreeMap objects
	 */
    public SplayTreeMap() {
        super(null);
    }

	/**
	 * The constructor used for creating SplayTreeMap objects with a specified comparator
	 * 
	 * @param compare the comparator containing the different type of comparing methods
	 */
    public SplayTreeMap(Comparator<K> compare) {
        super(compare);
    }

    /**
     * Utility used to rebalance after a map operation.
     * 
     * @param p the position being observed
     */
    private void splay(Position<Entry<K, V>> p) {
        
    	Position<Entry<K, V>> node = p;
    	
    	while (!isRoot(node)) {
    		
    		Position<Entry<K, V>> parent = parent(node);
    		Position<Entry<K, V>> grandparent = parent(parent);
    		
    		if (grandparent == null) {
    		    // ZIG
    		    // Perform a single rotation if there is no grandparent
    			rotate(node);
    		}
    		else if ((parent == left(grandparent)) == (p == left(parent))) {
    			// ZIG-ZIG
    			// Rotate the parent around grandparent first
    			rotate(parent);
    			// Then rotate the node around the parent
    			rotate(p);
    		}
    		else {
    			// ZIG-ZAG
    		    // Rotate node around parent
    			rotate(p);
    			// Then rotate node around grandparent
    			rotate(p);
    		}
    	}
    }

    @Override
    protected void actionOnAccess(Position<Entry<K, V>> p) {
        // If p is a dummy/sentinel node, move to the parent
        if(isLeaf(p)) {
            p = parent(p);
        }
        if(p != null) {
            splay(p);
        }
    }

    @Override
    protected void actionOnInsert(Position<Entry<K, V>> node) {
        splay(node);
    }

    @Override
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        if(!isRoot(p)) {
            splay(parent(p));
        }
    }
}
