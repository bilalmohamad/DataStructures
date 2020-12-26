package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * This class will be used for creating graph traversals
 * It will use the depthFirstSearch and breadthFirstSearch algorithms
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class GraphTraversalUtil {

	/**
	 * Creates depth-first traversal of the graph starting from the vertex
	 * 
	 * @param graph the graph used for the traversal
	 * @param start	the starting vertex
 	 * @param <V>	the object type of the vertex
	 * @param <E>	the object type of the edge
	 * 
	 * @return a depth-first traversal of the graph starting from the vertex
	 */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
        
    	Set<Vertex<V>> known = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	
    	dfsHelper(graph, start, known, forest);
    	
    	return forest;
    }
       
    /**
     * The helper method used to recursively create the traversal
     *  
     * @param graph 	the graph being traversed
     * @param v			the current vertex
     * @param known		the set storing the known vertices
     * @param forest 	the map to store the traversal
     */
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
    	
    	known.add(u);
    	
    	for (Edge<E> e : graph.outgoingEdges(u)) {
    		
    		Vertex<V> v = graph.opposite(u, e);
    		
    		if (!known.contains(v)) {
    			forest.put(v, e);
    			dfsHelper(graph, v, known, forest);
    		}
    	}
    }
    
    
	/**
	 * Creates breadth-first traversal of the graph starting from the vertex
	 * 
	 * @param graph the graph used for the traversal
	 * @param start	the starting vertex
	 * @param <V>	the object type of the vertex
	 * @param <E>	the object type of the edge
	 * 
	 * @return a breadth-first traversal of the graph starting from the vertex
	 */
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
        
    	Set<Vertex<V>> known = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	Queue<Vertex<V>> vertices = new ArrayBasedQueue<Vertex<V>>();
    	
    	known.add(start);
    	vertices.enqueue(start);
    	
    	while (!vertices.isEmpty()) {
    		
    		Vertex<V> u = vertices.dequeue();
    		
    		for (Edge<E> e : graph.outgoingEdges(u)) {
    			
    			Vertex<V> w = graph.opposite(u, e);
    			
    			if (!known.contains(w)) {
    				
    				known.add(w);
    				forest.put(w, e);
    				vertices.enqueue(w);
    			}
    		}
    	}
    	
    	return forest;
    }
}
