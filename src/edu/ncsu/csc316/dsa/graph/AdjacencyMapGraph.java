package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * This class creates an AdjacencyMapGraph by using the AbstractGraph parent class
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <V> The object type of the vertex
 * @param <E> The object type of the edge
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

	/** The list containing the vertices */
    private PositionalList<Vertex<V>> vertexList;
    
    /** The list containing the edges */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * The default constructor used for the AdjacencyMapGraph
     */
    public AdjacencyMapGraph() {
        this(false);
    }
    
    /**
     * The constructor used for creating an AdjacencyMapGraph
     * 
     * @param directed determines whether the graph will be directed or not
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        /*AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;*/
    	
    	AMVertex v = new AMVertex(vertexData, isDirected());
    	v.setPosition(vertexList.addLast(v));
    	
    	return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        /*GraphEdge edge = new GraphEdge(edgeData, v1, v2);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        AMVertex origin = validate(v1);
        AMVertex destination = validate(v2);
        // TODO Your code here
        // Remember to add the edge to the maps for each endpoint vertex
        origin.getOutgoing().put(v2, edge);
        destination.getIncoming().put(v1, edge);
        
        return edge;*/
    	
    	if (getEdge(v1, v2) == null) {
    		
    		GraphEdge e = new GraphEdge(edgeData, v1, v2);
    		e.setPosition(edgeList.addLast(e));
    		AMVertex origin = validate(v1);
    		AMVertex dest = validate(v2);
    		
    		origin.getOutgoing().put(v2, e);
    		dest.getIncoming().put(v1, e);
    		
    		return e;
    	}
    	else {
    		throw new IllegalArgumentException("Edge from u to v exists");
    	}
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        // TODO Your code here
        for (Edge<E> e : v.getOutgoing().values()) {
        	removeEdge(e);
        }
        
        for (Edge<E> e : v.getIncoming().values()) {
        	removeEdge(e);
        }
        
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = (Vertex<V>[]) e.getEndpoints();
        // TODO Your code here
        
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        
        origin.getOutgoing().remove(ends[1]);
        dest.getIncoming().remove(ends[0]);
        
        Edge<E> answer = edgeList.remove(e.getPosition());
        e.setPosition(null);
        
        return answer;
    }
    
    /**
     * Inner class for creating Map vertices that maintain incoming and outgoing edges
     *
     * @author Bilal Mohamad (bmohama)
     *
     */
    private class AMVertex extends GraphVertex {
        
    	/** The incoming edge to the vertex */
        private Map<Vertex<V>, Edge<E>> outgoing;
        
        /** The outgoing edge from the vertex */
        private Map<Vertex<V>, Edge<E>> incoming;
        
        /**
         * The constructor for making AMVertex objects
         * 
         * @param data			the value stored in the vertex
         * @param isDirected	determines if the graph is directed
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if(isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Returns a map of the outgoing edges
         * 
         * @return a map of the outgoing edges
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Returns a map of the incoming edges
         * 
         * @return a map of the incoming edges
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Checks if the vertex is valid
     * 
     * @param v the vertex being checked
     * 
     * @return an Adjacency Map Vertex object of the entered vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex)v;
    }
}
