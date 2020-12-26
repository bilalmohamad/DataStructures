package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * The abstract class that will be used by the other classes to create a graph
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <V> The object type of the vertex
 * @param <E> The object type of the edge
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    
	/** Boolean flag used to determine if the graph is directed */
    private boolean isDirected;
    
    /**
     * The constructor used for creating an AbstractGraph object
     * 
     * @param directed used to determine if the graph is directed or not
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    /**
     * Sets the directed value to the entered parameter
     * 
     * @param directed used to determine if the graph is directed or not
     */
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * Checks if the graph is directed or not
     * 
     * @return	true	if the graph is directed
     * 			false	otherwise
     */
    public boolean isDirected() {
        return isDirected;
    }
    
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if(ends[0] == vertex) {
            return ends[1];
        }
        if(ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * The inner class used for creating the individual vertices for the graph
     *
     * @author Bilal Mohamad (bmohama)
     *
     */
    protected class GraphVertex implements Vertex<V> {
    	
    	/** The element of the vertex */
        private V element;
        
        /** The position of the vertex */
        private Position<Vertex<V>> position;
        
        /**
         * The constructor used for creating GraphVertex objects
         * 
         * @param element the element to store in the GraphVertex
         */
        public GraphVertex(V element) {
            setElement(element);
        }
        
        /**
         * Sets the element variable to the entered parameter
         * 
         * @param element the element to store in the GraphVertex
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         * Retrieves the element of the GraphVertex
         * 
         * @return the element of the GraphVertex
         */
        public V getElement() {
            return element;
        }
        
        /**
         * Retrieves the position of the GraphVertex
         * 
         * @return the position of the GraphVertex
         */
        public Position<Vertex<V>> getPosition(){
            return position;
        }
        
        /**
         * Sets the GraphVertex's position to the entered parameter
         * 
         * @param pos the position to set the GraphVertex's position to
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }
    
    /**
     * The inner class used for creating the individual edges for the graph
     *
     * @author Bilal Mohamad (bmohama)
     *
     */
    protected class GraphEdge implements Edge<E> {
    	
    	/** The element of the edge */
        private E element;
        
        /** The vertices that connect the edge */
        private Vertex<V>[] endpoints;
        
        /** The position of the edge */
        private Position<Edge<E>> position;
        
        /**
         * The constructor used for creating GraphEdge objects
         * 
         * @param element the element to store in the edge
         * 
         * @param v1 the starting vertex
         * @param v2 the ending vertex
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[])new Vertex[]{v1, v2};
        }
        
        /**
         * Sets the element to the parameter
         * 
         * @param element the new element to store
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Returns the element of the edge
         * 
         * @return the element of the edge
         */
        public E getElement() {
            return element;
        }
        
        /**
         * Returns the endpoints of the edge
         * 
         * @return the endpoints of the edge
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        
        /**
         * Returns the position of the edge
         * 
         * @return the position of the edge
         */
        public Position<Edge<E>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position to the parameter
         * 
         * @param pos the new position to store
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }
    
    /**
     * Checks if the edge is valid and returns a GraphEdge object
     * 
     * @param e	the edge being observed
     * 
     * @return the newly created GraphEdge made from the entered edge
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}
