package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Creates a General Tree that contains the basic methods required to make a tree
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public class GeneralTree<E> extends AbstractTree<E> implements GeneralTreeCollection<E> {

	/** The root of the tree */
    private Node<E> root;
    
    /** The size of the tree */
    private int size;
    
    
    /**
     * The constructor used for creating GeneralTree objects
     */
    public GeneralTree() {
        root = null;
        size = 0;
    }
    
    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        return validate(p).getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        for(Position<Node<E>> n : node.getChildren().positions())
        {
            ret.addLast(n.getElement());
        }
        return ret;
    }

    @Override
    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }
    
    @Override
    public int size() {
        return size;
    }    

    @Override
    public Position<E> addRoot(E value) {
        if(root != null) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        this.root = createNode(value);
        size = 1;
        return root;
    }    
    
    @Override
    public Position<E> addChild(Position<E> p, E value) {
        Node<E> node = validate(p);
        Node<E> newNode = createNode(value);
        node.getChildren().addLast(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }
    
    @Override
    public E set(Position<E> p, E value) {
        Node<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }    
    
    /**
     * Creates a new node for the tree
     * 
     * @param element	the value of the node
     * 
     * @return the node that was created
     */
    public Node<E> createNode(E element) {
        return new Node<E>(element);
    }
	
    /**
     * Checks if the position is a valid position within the tree
     * 
     * @param p	the position being checked
     * 
     * @throws IllegalArgumentException if a node does not exist at the entered position
     * 
     * @return the node at the entered position
     */
    private Node<E> validate(Position<E> p) {
        if(!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a legal general tree node");
        }
        return (Node<E>)p;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(preOrder().iterator());
    }

	@Override
	public E remove(Position<E> p) {
		
	    //Your remove may follow something similar to the following, though you might need to add special cases 
	    //(for example, if the root is being removed, updating size, updating parent pointers, etc.):
		
		if (numChildren(p) > 1) {
			throw new IllegalArgumentException();
		}
		Node<E> currentNode = validate(p);
		Node<E> currentParent = validate(parent(p));
		E answer = currentNode.getElement();
		
		if (isRoot(currentNode) && numChildren(p) == 1) {
			root = currentNode.getChildren().first().getElement();
			size = 1;
			
			return answer;
		}
		
		if (isRoot(currentNode) && size == 1) {
			root = null;
			size = 0;
			
			return answer;
		}
		
		// Get an iterator over the positions that are parent's children
		Iterator<Position<Node<E>>> it = currentParent.getChildren().positions().iterator();
		
		// Iterate through the parent's children until we find the node being removed
		while (it.hasNext()) {
			Position<Node<E>> current = it.next();
			
			// if we found the node we want to remove
			if (current.getElement() == currentNode) {
				if (numChildren(p) == 1) {
					// If there was one child, replace the position with the node's child
					currentParent.getChildren().set(current, currentNode.getChildren().first().getElement());
				}
				else {
			        // If the node has no children, then 
			        //  remove the node from the list of the parent's children
					currentParent.getChildren().remove(current);
				}
			}
		}
		
		size--;
		return answer;
	}

	/**
	 * Creates Nodes for the GeneralTree
	 * 
	 * @author Bilal Mohamad
	 *
	 * @param <E> The object type
	 */
	private static class Node<E> extends AbstractNode<E> {

		/** The parent of the Node */
        private Node<E> parent;
        
        // A general tree node needs to maintain a list of children
        /** A PositionalList containing a list of the positions of all the children of the node */
        private PositionalList<Node<E>> children;
        
        /**
         * The constructor used for creating the individual Nodes without a parent
         * 
         * @param element	the value to set to the Node
         */
        public Node(E element) {
            this(element, null);
        }
        
        /**
         * The constructor used for creating the individual Nodes without a parent
         * 
         * @param element	the value to set to the Node
         * @parem parent	the parent Node of the current Node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
            children = new PositionalLinkedList<Node<E>>();
        }
        
        /**
         * Sets the parent node of the current Node to the entered Node
         * 
         * @param p	the Node to set the current parent to
         */
        public void setParent(Node<E> p) {
            parent = p;
        }
        
        /**
         * Retrieves the parent of the node
         * 
         * @return the parent of the node
         */
        public Node<E> getParent() {
            return parent;
        }
        
        /**
         * Retrieves a PositionalList of all the children of the Node
         * 
         * @return a PositionalList of all the children of the Node
         */
        public PositionalList<Node<E>> getChildren() {
            return children;
        }
    }
}
