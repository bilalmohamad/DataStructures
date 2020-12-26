package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * This class will be used for creating shortest path algorithms
 * It will use the dijkstra and shortestPathTree algorithms
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class ShortestPathUtil {	
	
	/**
	 * Finds the shortest path using the dijkstra algorithm
	 * 
	 * @param g		the graph witht the all the paths
	 * @param src	the starting vertex
	 * @param <V>	the object type of the vertex
	 * @param <E>	the object type of the edge
	 * 
	 * @return a map containing each vertex and their individual weight
	 */
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> g, Vertex<V> src) {
        // TODO Your code here
        // You may want to review the Prim-Jarnik implementation below first, 
        //    since Dijkstra's algorithm is similar to Prim-Jarnik's algorithm
    	
    	AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
    	
    	Map<Vertex<V>, Integer> d = new LinearProbingHashMap<>();
    	Map<Vertex<V>, Integer> cloud = new LinearProbingHashMap<>();
    	Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens = new LinearProbingHashMap<>();
    	
    	for (Vertex<V> v : g.vertices()) {
    	
    		if (v.equals(src)) {
    			d.put(v, 0);
    		}
    		else {
    			d.put(v, Integer.MAX_VALUE);
    		}
    		
    		pqTokens.put(v, pq.insert(d.get(v), v));
    	}
    	
    	while (!pq.isEmpty()) {
    		
    		Entry<Integer, Vertex<V>> entry = pq.deleteMin();
    		int key = entry.getKey();
    		Vertex<V> u = entry.getValue();
    		cloud.put(u, key);
    		pqTokens.remove(u);
    		
    		for (Edge<E> e : g.outgoingEdges(u)) {
    			
    			Vertex<V> v = g.opposite(u, e);
    			
    			if (cloud.get(v) == null) {
    				int weight = e.getElement().getWeight();
    				
    				if (d.get(u) + weight < d.get(v)) {
    					d.put(v, d.get(u) + weight);
    					pq.replaceKey(pqTokens.get(v), d.get(v));
    				}
    			}
    		}
    	}
    	
    	return cloud;
    }
    
	/**
	 * Finds the shortest path using the shortestPathTree algorithm
	 * 
	 * @param g		the graph witht the all the paths
	 * @param s		the starting vertex
	 * @param distances the map containing the distances of the path
	 * @param <V>	the object type of the vertex
	 * @param <E>	the object type of the edge
	 * 
	 * @return a map containing each vertex and their individual weight
	 */
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> g, Vertex<V> s, Map<Vertex<V>, Integer> distances) {
        
    	Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<>();
    	
    	for (Vertex<V> v : distances) {
    		if (!v.equals(s)) {
    			for (Edge<E> e : g.incomingEdges(v)) {
    				Vertex<V> u = g.opposite(v, e);
    				
    				if (distances.get(v).equals(distances.get(u) + e.getElement().getWeight())) {
    					m.put(v, e);
    				}
    			}
    		}
    	}
    	
    	return m;
    }
}
