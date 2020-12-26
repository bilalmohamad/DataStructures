package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This class creates an AdjacencyListGraph by using the AbstractGraph parent class
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <V> The object type of the vertex
 * @param <E> The object type of the edge
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

	/** The list containing the vertices */
    private PositionalList<Vertex<V>> vertexList;
    
    /** The list containing the edges */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * The default constructor used for the AdjacencyListGraph
     */
    public AdjacencyListGraph() {
        this(false);
    }
    
    /**
     * The constructor used for creating an AdjacencyListGraph
     * 
     * @param directed determines whether the graph will be directed or not
     */
    public AdjacencyListGraph(boolean directed) {
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
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == vertex1 && ends[0] == vertex2) {
                return current;
            }
            if (ends[0] == vertex1 && ends[1] == vertex2) {
                return current;
            }
        }
        return null;
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
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        ALEdge edge = new ALEdge(edgeData, v1, v2);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        // TODO Your code here
        // Remember to set the edge's positions in the outgoingEdges 
        //    and incomingEdges lists for the appropriate vertices
        ALVertex origin = validate(v1);
        ALVertex destination = validate(v2);
        
        edge.setOutgoingListPosition(origin.getOutgoing().addLast(edge));
        edge.setIncomingListPosition(destination.getIncoming().addLast(edge));
        
//        origin.getOutgoing().addLast(edge);
//        destination.getIncoming().addLast(edge);
        
        return edge;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
        // TODO Your code here
		for(Edge<E> e: outgoingEdges(v)) {
			removeEdge(e);
		}
		for(Edge<E> e: incomingEdges(v)) {
			removeEdge(e);
		}
		return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        ALEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        
        Edge<E> answer = edgeList.remove(e.getPosition());
        
        origin.getIncoming().remove(e.getIncomingListPosition());
        origin.getOutgoing().remove(e.getOutgoingListPosition());
        
        dest.getIncoming().remove(e.getIncomingListPosition());
        dest.getOutgoing().remove(e.getOutgoingListPosition());        
        
        return answer;
    }
    
    /**
     * Inner class for creating a list of vertices that maintain incoming and outgoing edges
     *
     * @author Bilal Mohamad (bmohama)
     *
     */
    private class ALVertex extends GraphVertex {
        
    	/** A list containing the the outgoing edges */
        private PositionalList<Edge<E>> outgoing;
        
        /** A list containing the the incoming edges */
        private PositionalList<Edge<E>> incoming;
        
        /**
         * The constructor for making ALVertex objects
         * 
         * @param data			the value stored in the vertex
         * @param isDirected	determines if the graph is directed
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if(isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Returns a positional list of the outgoing edges
         * 
         * @return a positional list of the outgoing edges
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Returns a positional list of the incoming edges
         * 
         * @return a positional list of the incoming edges
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Inner class for creating individual positions of both incoming and outgoing
     *
     * @author Bilal Mohamad (bmohama)
     *
     */
    private class ALEdge extends GraphEdge {    
    	
    	/** The position of the outgoing list position */
        private Position<Edge<E>> outgoingListPosition;
        
        /** The position of the incoming list position */
        private Position<Edge<E>> incomingListPosition;
        
        /**
         * The constructor used for creating ALEdge objects
         * 
         * @param element	the value stored in the edge
         * @param v1		the starting vertex
         * @param v2		the ending vertex
         */
        public ALEdge(E element, Vertex<V> v1,
                Vertex<V> v2) {
            super(element, v1, v2);
        }
        
        /**
         * Returns the outgoing list position
         * 
         * @return the outgoing list position
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }
        
        /**
         * Sets the outgoing list position to the entered parameter
         * 
         * @param outgoingListPosition the new position
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
        
        /**
         * Returns the incoming list position
         * 
         * @return the incoming list position
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }
        
        /**
         * Sets the incoming list position to the entered parameter
         * 
         * @param incomingListPosition the new position
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    /**
     * Checks if the vertex is valid
     * 
     * @param v the vertex being checked
     * 
     * @return an Adjacency List Vertex object of the entered vertex
     */
    private ALVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex)v;
    }
    
    /**
     * Checks if the edge is valid
     * 
     * @param e the edge being checked
     * 
     * @return an Adjacency List Edge object of the entered edge
     */
    protected ALEdge validate(Edge<E> e) {
        if(!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge)e;
    }
}
