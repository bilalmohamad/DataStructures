package edu.ncsu.csc316.dsa.disjoint_set;


import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Creates a forest of Disjoint Sets while implementing the DisjointSet interface
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <E> the object type
 */
public class UpTreeDisjointSetForest<E> implements DisjointSet<E> {

    // We need a secondary map to quickly locate an entry within the forest of up-trees
    // NOTE: The textbook implementation does not include this
    //       functionality; instead, the textbook implementation leaves
    //       the responsibility of tracking positions to the client in a
    //       separate map structure
	/** The map containing the forest */
    private Map<E, UpTreeNode<E>> map;
    
    
    /**
     * The constructor used for creating UpTreeDisjointSetForest objects
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }
    
    
    @Override
    public Position<E> makeSet(E value) {
    	UpTreeNode<E> node = new UpTreeNode<E>(value);
    	node.setParent(null);
    	node.setCount(1);
    	
    	map.put(value, node);
    	
    	return node;
    }

    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        //       track of the location of each position in the forest.
        //       Our implementation includes a Map to handle this for the 
        //       client, so we should allow the client to find the set
        //       that contains a node by specifying the element
        // TODO your code here
    	
    	/*Iterator<UpTreeNode<E>> iter = map.values().iterator();
    	UpTreeNode<E> node = null;
    	
    	while (iter.hasNext()) {
    		node = iter.next();
    		
    		if (node.getElement().equals(value)) {
    			break;
    		}
    	}
    	
    	node.setParent(findHelper(node));*/
    	
    	UpTreeNode<E> node = validate(map.get(value));
    	
    	while (node.getParent() != null) {
    		node = node.getParent();
    	}
    	
    	return node;
    }
    
    /*private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        // TODO your code here
        // Implement path-compression find
    	/*if (current.parent != current && current.parent != null) {
    		current.parent = findHelper(current.parent);
    	}
    	return current.parent;*/
    	/*if (current != current.parent && current.parent != null) {
    		current.parent = findHelper(current.parent);
    	}
    	return current.parent;
    }*/

    @Override
    public void union(Position<E> s, Position<E> t) {
        // Instead of trusting the client to give us the roots
        // of two up-trees, we will verify by finding the root 
        // of the up-tree that contains the element in positions s and t
        UpTreeNode<E> a = validate(find(s.getElement()));
        UpTreeNode<E> b = validate(find(t.getElement()));
        
        if (a.getCount() >= b.getCount()) {
        	a.setCount(a.getCount() + b.getCount());
        	b.setParent(a);
        }
        else {
        	b.setCount(a.getCount() + b.getCount());
        	a.setParent(b);
        }
    }
    
    
    /**
     * Ensures that the entered position is a valid position
     * 
     * @param p	the position being observed
     * 
     * @return an UpTreeNode of the position
     */
    private UpTreeNode<E> validate(Position<E> p) {
        if(!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>)p;
    }
    
    
    /**
     * The inner class used for creating individual UpTreeNodes
     *
     * @author Bilal Mohamad (bmohama)
     *
     * @param <E> the object type
     */
    private static class UpTreeNode<E> implements Position<E> {
        
    	/** The element of the node */
        private E element;
        
        /** The parent of the node */
        private UpTreeNode<E> parent;
        
        /** The count of the node */
        private int count;
        
        
        /**
         * The constructor used for creating UpTreeNode objects
         * 
         * @param element the element to be stored in the node
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }
        
        
        /**
         * Sets the node's current element to the entered element
         * 
         * @param element the element to set the current element to
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        
        @Override
        public E getElement() {
            return element;
        }
        
        
        /**
         * Sets the node's current parent to the entered parent
         * 
         * @param parent the parent to set the current parent to
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }
        
        
        /**
         * Retrieves the parent of the node
         * 
         * @return the parent of the node
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }
        
        
        /**
         * Sets the node's current count to the entered count
         * 
         * @param count the count to set the current count to
         */
        public void setCount(int count) {
            this.count = count;
        }
        
        
        /**
         * Retrieves the count of the node
         * 
         * @return the count of the node
         */
        public int getCount() {
            return count;
        }
    }   
}