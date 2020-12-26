package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Highway;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Tests the ShortestPathUtil class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class ShortestPathUtilTest {

	/**
	 * Tests the dijkstra method
	 */
	@Test
	public void testDijkstra() {
		
		AbstractGraph<String, Highway> graph = new AdjacencyListGraph<String, Highway>();
		assertEquals(false, graph.isDirected());
		assertEquals(0, graph.numVertices());
		assertEquals(0, graph.numEdges()); 
		
		//Inserts vertices
		Vertex<String> vertA = graph.insertVertex("A");
		Vertex<String> vertB = graph.insertVertex("B");
		Vertex<String> vertC = graph.insertVertex("C");
		Vertex<String> vertD = graph.insertVertex("D");
		Vertex<String> vertE = graph.insertVertex("E");
		Vertex<String> vertF = graph.insertVertex("F");
		assertEquals(6, graph.numVertices());
		
		//Inserts edges
		Edge<Highway> edgeAB = graph.insertEdge(vertA, vertB, new Highway("AB", 3));
		Edge<Highway> edgeAF = graph.insertEdge(vertA, vertF, new Highway("AF", 7));
		Edge<Highway> edgeBC = graph.insertEdge(vertB, vertC, new Highway("BC", 5));
		Edge<Highway> edgeBE = graph.insertEdge(vertB, vertE, new Highway("BE", 5));
		Edge<Highway> edgeCD = graph.insertEdge(vertC, vertD, new Highway("CD", 9));
		Edge<Highway> edgeCE = graph.insertEdge(vertC, vertE, new Highway("CE", 4));
		Edge<Highway> edgeDE = graph.insertEdge(vertD, vertE, new Highway("DE", 8));
		Edge<Highway> edgeDF = graph.insertEdge(vertD, vertF, new Highway("DF", 5));
		Edge<Highway> edgeEF = graph.insertEdge(vertD, vertE, new Highway("EF", 6));
		assertEquals(9, graph.numEdges());
		assertEquals("AB", edgeAB.getElement().getName());
		assertEquals("AF", edgeAF.getElement().getName());
		assertEquals("BC", edgeBC.getElement().getName());
		assertEquals("BE", edgeBE.getElement().getName());
		assertEquals("CD", edgeCD.getElement().getName());
		assertEquals("CE", edgeCE.getElement().getName());
		assertEquals("DE", edgeDE.getElement().getName());
		assertEquals("DF", edgeDF.getElement().getName());
		assertEquals("EF", edgeEF.getElement().getName());
		
		
		Map<Vertex<String>, Integer> map = ShortestPathUtil.dijkstra(graph, vertA);
		assertEquals(6, map.size());
		
		
		/*Iterator<Vertex<String>> it = map.iterator();
		System.out.println("Dijkstra");
		while (it.hasNext()) {
			System.out.println(it.next().getElement());
		}
		System.out.println();*/
		
		/*assertEquals(vertA, it.next());
		assertEquals(vertB, it.next());
		assertEquals(vertC, it.next());
		assertEquals(vertE, it.next());
		assertEquals(vertF, it.next());
		assertEquals(vertD, it.next());*/	
	}
	
	/**
	 * Tests the shortestPathTree method
	 */
	@Test
	public void testShortestPathTree() {
		
		AbstractGraph<String, Highway> graph = new AdjacencyListGraph<String, Highway>();
		assertEquals(false, graph.isDirected());
		assertEquals(0, graph.numVertices());
		assertEquals(0, graph.numEdges()); 
		
		//Inserts vertices
		Vertex<String> vertA = graph.insertVertex("A");
		Vertex<String> vertB = graph.insertVertex("B");
		Vertex<String> vertC = graph.insertVertex("C");
		Vertex<String> vertD = graph.insertVertex("D");
		Vertex<String> vertE = graph.insertVertex("E");
		Vertex<String> vertF = graph.insertVertex("F");
		assertEquals(6, graph.numVertices());
		
		//Inserts edges
		Edge<Highway> edgeAB = graph.insertEdge(vertA, vertB, new Highway("AB", 3));
		Edge<Highway> edgeAF = graph.insertEdge(vertA, vertF, new Highway("AF", 7));
		Edge<Highway> edgeBC = graph.insertEdge(vertB, vertC, new Highway("BC", 5));
		Edge<Highway> edgeBE = graph.insertEdge(vertB, vertE, new Highway("BE", 5));
		Edge<Highway> edgeCD = graph.insertEdge(vertC, vertD, new Highway("CD", 9));
		Edge<Highway> edgeCE = graph.insertEdge(vertC, vertE, new Highway("CE", 4));
		Edge<Highway> edgeDE = graph.insertEdge(vertD, vertE, new Highway("DE", 8));
		Edge<Highway> edgeDF = graph.insertEdge(vertD, vertF, new Highway("DF", 5));
		Edge<Highway> edgeEF = graph.insertEdge(vertD, vertE, new Highway("EF", 6));
		assertEquals(9, graph.numEdges());
		assertEquals("AB", edgeAB.getElement().getName());
		assertEquals("AF", edgeAF.getElement().getName());
		assertEquals("BC", edgeBC.getElement().getName());
		assertEquals("BE", edgeBE.getElement().getName());
		assertEquals("CD", edgeCD.getElement().getName());
		assertEquals("CE", edgeCE.getElement().getName());
		assertEquals("DE", edgeDE.getElement().getName());
		assertEquals("DF", edgeDF.getElement().getName());
		assertEquals("EF", edgeEF.getElement().getName());
		
		Map<Vertex<String>, Integer> distances = new LinearProbingHashMap<>();
		distances.put(vertA, 0);
		distances.put(vertB, 0);
		distances.put(vertC, 0);
		distances.put(vertE, 0);
		distances.put(vertF, 0);
		distances.put(vertD, 0);
		
		
		
		Map<Vertex<String>, Edge<Highway>> map = ShortestPathUtil.shortestPathTree(graph, vertA, distances);
		assertNotNull(map);
		
		
		/*Iterator<Vertex<String>> it = map.iterator();
		System.out.println("Dijkstra");
		while (it.hasNext()) {
			System.out.println(it.next().getElement());
		}
		System.out.println();*/
		
		/*assertEquals(vertA, it.next());
		assertEquals(vertB, it.next());
		assertEquals(vertC, it.next());
		assertEquals(vertE, it.next());
		assertEquals(vertF, it.next());
		assertEquals(vertD, it.next());*/	
	}
}
