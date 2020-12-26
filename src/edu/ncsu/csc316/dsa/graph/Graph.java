package edu.ncsu.csc316.dsa.graph;

/**
 * The interface used for creating graphs
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <V> the object type of the vertex
 * @param <E> the object type of the edge
 */
public interface Graph<V, E> {
	
	/**
	 * Checks if the graph is directed
	 * 
	 * @return	true	if the graph is directed
	 * 			false	otherwise
	 */
    boolean isDirected();
    
    /**
     * Retrieves the number of vertices in the graph
     * 
     * @return the number of vertices in the graph
     */
    int numVertices();
    
    /**
     * Retrieves an iteration of all the vertices in the graph
     * 
     * @return an iteration of all the vertices in the graph
     */
    Iterable<Vertex<V>> vertices();
    
    /**
     * Retrieves the number of edges in the graph
     * 
     * @return the number of edges in the graph
     */
    int numEdges();
    
    /**
     * Retrieves an iteration of all the edges in the graph
     * 
     * @return an iteration of all the edges in the graph
     */
    Iterable<Edge<E>> edges();
    
    /**
     * Retrieves the edges that connects the entered vertices.
     * If there is no edge, returns null.
     * 
     * @param vertex1	the first vertex
     * @param vertex2	the second vertex
     * 
     * @return the edge that connects the vertices, otherwise null
     */
    Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);
    
    /**
     * Returns the two end-point vertices of edge e. 
     * For a directed graph, the first vertex is the source vertex and 
     * the second is the destination vertex
     * 
     * @param edge the edge being observed
     * 
     * @return an array containing the two vertices that connect the edge
     */
    Vertex<V>[] endVertices(Edge<E> edge);
    
    /**
     * Returns the other vertex of the edge, given an edge incident to vertex.
     * 
     * @param vertex the vertex connecting the opposing vertex
     * @param edge the edge connecting the two vertices
     * 
     * @return the other vertex of the edge
     */
    Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);
    
    /**
     * Returns the number of outgoing edges from the vertex
     * 
     * @param vertex the vertex being observed
     * 
     * @return the number of outgoing edges from the vertex
     */
    int outDegree(Vertex<V> vertex);
    
    /**
     * Returns the number of incoming edges to vertex. 
     * For undirected graphs, outDegree(v) = inDegree(v)
     * 
     * @param vertex the vertex being observed
     * 
     * @return the number of incoming edges to the vertex
     */
    int inDegree(Vertex<V> vertex);
    
    /**
     * Returns an iteration of all outgoing edges from vertex
     * 
     * @param vertex the vertex being observed
     * 
     * @return an iteration of all outgoing edges from vertex
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);
    
    /**
     * Returns an iteration of all incoming edges from vertex
     * For undirected graphs, ougoingEdges(v) = incomingEdges(v)
     * 
     * @param vertex the vertex being observed
     * 
     * @return an iteration of all incoming edges from vertex
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);
    
    /**
     * Creates and returns a new vertex storing the element
     * 
     * @param vertexData the element of the vertex
     * 
     * @return the new vertex
     */
    Vertex<V> insertVertex(V vertexData);
    
    /**
     * Creates and returns a new Edge from vertex v1 to vertex v2 and stores the element
     * 
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @param edgeData the element of the edge
     * 
     * @return the new edge
     */
    Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);
    
    /**
     * Removes vertex and all its incident edges from the graph
     * 
     * @param vertex the vertex being removed
     * 
     * @return the removed vertex
     */
    Vertex<V> removeVertex(Vertex<V> vertex);
    
    /**
     * Removes the edge from the graph
     * 
     * @param edge the edge being removed
     * 
     * @return the removed edge
     */
    Edge<E> removeEdge(Edge<E> edge);
    
    /**
     * The inner interface for the edge
     *
     * @author Bilal Mohamad (bmohama)
     *
     * @param <E> the object type of the edge
     */
    interface Edge<E> {
    	
    	/**
    	 * Retrieves the element stored in the edge
    	 * 
    	 * @return the element stored in the edge
    	 */
        E getElement();
    }
    
    /**
     * The inner interface for the vertex
     *
     * @author Bilal Mohamad (bmohama)
     *
     * @param <V> the object type of the vertex
     */
    interface Vertex<V> {
    	
    	/**
    	 * Retrieves the element stored in the vertex
    	 * 
    	 * @return the element stored in the vertex
    	 */
        V getElement();
    }
}
