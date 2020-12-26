package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Creates a LinkedBinaryTree by using the AbstractBinaryTree
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** The root of the tree */
    private Node<E> root;
    
    /** The size of the tree */
    private int size;

    /**
     * The constructor used for creating LinkedBinaryTree objects
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Checks if the position is a valid node within the tree
     * 
     * @param p	the position being checked
     * 
     * @throws IllegalArgumentException if a node does not exist at the entered position
     * 
     * @return the node at the entered position
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }

    
	/**
	 * Creates Nodes for the LinkedBinaryTree
	 * 
	 * @author Bilal Mohamad
	 *
	 * @param <E> The object type
	 */
    public static class Node<E> extends AbstractNode<E> {
    	
    	/** The parent of the Node */
        private Node<E> parent;
        
        /** The Node to the left of the parent */
        private Node<E> left;
        
        /** The Node to the right of the parent */
        private Node<E> right;

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
         * @param parent	the parent Node of the current Node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Retrieves the node left of the current position
         * 
         * @return the node left of the current position
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Retrieves the node right of the current position
         * 
         * @return the node right of the current position
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Sets the current position node's left node to the entered node
         * 
         * @param left the node left of the current position
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * Sets the current position node's right node to the entered node
         * 
         * @param right the node right of the current position
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Retrieves the parent node of the current position
         * 
         * @return the parent node of the current position
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Sets the current position node's parent node to the entered node
         * 
         * @param parent the parent node of the current position
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> parent = validate(p);
        
        if (left(parent) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        
        Node<E> child = createNode(value, parent, null, null);
        parent.setLeft(child);
        size++;
        
        return child;
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> parent = validate(p);
        
        if (right(parent) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        
        Node<E> child = createNode(value, parent, null, null);
        parent.setRight(child);
        size++;
        
        return child;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        
        return root;
    }

    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        
        Node<E> node = validate(p);
        
        //TODO CHANGE TO BE IN TERMS I UNDERSTAND
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        
        if (child != null) {
        	child.setParent(node.getParent());
        }
        
        if (node == root) {
        	root = child;
        }
        else {
        	Node<E> parent = node.getParent();
        	if (node == parent.getLeft()) {
        		parent.setLeft(child);
        	}
        	else {
        		parent.setRight(child);
        	}
        }
        
        size--;
        E answer = node.getElement();
        
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);
        
        return answer;
        
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a new node with a specific value, as well as a parent, left, and right node
     * 
     * @param e			the value of the new node
     * @param parent	the parent node of the new node
     * @param left		the left node of the new node
     * @param right		the right node of the new node
     * 
     * @return the newly created node
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    /**
     * Sets the current root to the entered position
     * 
     * @param p	the position of the new root
     * 
     * @return the original root
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}
