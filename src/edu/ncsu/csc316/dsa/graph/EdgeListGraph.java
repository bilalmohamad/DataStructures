package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This class creates an EdgeListGraph by using the AbstractGraph parent class
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <V> The object type of the vertex
 * @param <E> The object type of the edge
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {

	/** The list containing the vertices */
    private PositionalList<Vertex<V>> vertexList;
    
    /** The list containing the edges */
    private PositionalList<Edge<E>> edgeList;

    /**
     * The default constructor used for the EdgeListGraph
     */
    public EdgeListGraph() {
        this(false);
    }
    
    /**
     * The constructor used for creating an EdgeListGraph
     * 
     * @param directed determines whether the graph will be directed or not
     */
    public EdgeListGraph(boolean directed) {
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
    
    /**
     * The helper method used for validate the specified vertex
     * 
     * @param v the vertex to check
     * 
     * @return a GraphVertex object of the vertex
     */
    private GraphVertex validate(Vertex<V> v) {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
        }
        return (GraphVertex) v;
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
        return outgoingEdgeList(vertex).size();
    }
    
    /**
     * The helper method used for obtaining the list of the outgoing edges
     * 
     * @param vertex the vertex being observed
     * 
     * @return a list containing the outgoing edges of the vertex
     */
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while(it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if(ends[0] == vertex) {
                list.addLast(edge);
            }
            else if(!isDirected() && ends[1] == vertex)
            {
                list.addLast(edge);
            }
        }
        return list;
    }
    
    /**
     * The helper method used for obtaining the list of the incoming edges
     * 
     * @param vertex the vertex being observed
     * 
     * @return a list containing the incoming edges of the vertex
     */
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while(it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if(ends[1] == vertex) {
                list.addLast(edge);
            }
            else if(!isDirected() && ends[0] == vertex)
            {
                list.addLast(edge);
            }
        }
        return list;
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        GraphVertex v = new GraphVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        GraphEdge e = new GraphEdge(edgeData, v1, v2);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        return e;
    }

	@Override
	public Vertex<V> removeVertex(Vertex<V> vertex) {
		GraphVertex v = validate(vertex);
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
        GraphEdge e = validate(edge);
        return edgeList.remove(e.getPosition());
    }
}