package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the GraphTraversalUtil class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class GraphTraversalUtilTest {

	/**
	 * Tests the depthFirstSearch method
	 */
	@Test
	public void testDepthFirstSearch() {
		
		//Directed graph
		AbstractGraph<String, Integer> graph = new AdjacencyListGraph<String, Integer>(true);
		assertEquals(true, graph.isDirected());
		assertEquals(0, graph.numVertices());
		assertEquals(0, graph.numEdges());
		
		
		//Inserts vertices
		Vertex<String> day1 = graph.insertVertex("Day 1");
		Vertex<String> day2 = graph.insertVertex("Day 2");
		Vertex<String> day3 = graph.insertVertex("Day 3");
		Vertex<String> day4 = graph.insertVertex("Day 4");
		Vertex<String> day5 = graph.insertVertex("Day 5");
		assertEquals(5, graph.numVertices());
		
		//Inserts edges
		Edge<Integer> edge12 = graph.insertEdge(day1, day2, 85);
		Edge<Integer> edge13 = graph.insertEdge(day1, day3, 180);
		Edge<Integer> edge14 = graph.insertEdge(day1, day4, 255);
		Edge<Integer> edge15 = graph.insertEdge(day1, day5, 500);
		Edge<Integer> edge23 = graph.insertEdge(day2, day3, 65);
		Edge<Integer> edge24 = graph.insertEdge(day2, day4, 90);
		Edge<Integer> edge25 = graph.insertEdge(day2, day5, 220);
		Edge<Integer> edge34 = graph.insertEdge(day3, day4, 55);
		Edge<Integer> edge35 = graph.insertEdge(day3, day5, 90);
		Edge<Integer> edge45 = graph.insertEdge(day4, day5, 50);
		assertEquals(10, graph.numEdges());
		assertNotNull(edge12);
		assertNotNull(edge13);
		assertNotNull(edge14);
		assertNotNull(edge15);
		assertNotNull(edge23);
		assertNotNull(edge24);
		assertNotNull(edge25);
		assertNotNull(edge34);
		assertNotNull(edge35);
		assertNotNull(edge45);
		
		
		//Tests the map made from the traversal
		//TODO CHECK IF WRONG
		Map<Vertex<String>, Edge<Integer>> map1 = GraphTraversalUtil.depthFirstSearch(graph, day1);
		assertEquals(4, map1.size());
		assertEquals(edge12, map1.get(day2));
		assertEquals(edge23, map1.get(day3));
		assertEquals(edge34, map1.get(day4));
		assertEquals(edge45, map1.get(day5));
		
		Map<Vertex<String>, Edge<Integer>> map2 = GraphTraversalUtil.depthFirstSearch(graph, day2);
		assertEquals(3, map2.size());
		assertEquals(edge23, map2.get(day3));
		assertEquals(edge34, map2.get(day4));
		assertEquals(edge45, map2.get(day5));
	}
	
	
	/**
	 * Tests the breadthFirstSearch method
	 */
	@Test
	public void testBreadthFirstSearch() {
		
		//Directed graph
		AbstractGraph<String, Integer> graph = new AdjacencyListGraph<String, Integer>(true);
		assertEquals(true, graph.isDirected());
		assertEquals(0, graph.numVertices());
		assertEquals(0, graph.numEdges());
		
		
		//Inserts vertices
		Vertex<String> day1 = graph.insertVertex("Day 1");
		Vertex<String> day2 = graph.insertVertex("Day 2");
		Vertex<String> day3 = graph.insertVertex("Day 3");
		Vertex<String> day4 = graph.insertVertex("Day 4");
		Vertex<String> day5 = graph.insertVertex("Day 5");
		assertEquals(5, graph.numVertices());
		
		//Inserts edges
		Edge<Integer> edge12 = graph.insertEdge(day1, day2, 85);
		Edge<Integer> edge13 = graph.insertEdge(day1, day3, 180);
		Edge<Integer> edge14 = graph.insertEdge(day1, day4, 255);
		Edge<Integer> edge15 = graph.insertEdge(day1, day5, 500);
		Edge<Integer> edge23 = graph.insertEdge(day2, day3, 65);
		Edge<Integer> edge24 = graph.insertEdge(day2, day4, 90);
		Edge<Integer> edge25 = graph.insertEdge(day2, day5, 220);
		Edge<Integer> edge34 = graph.insertEdge(day3, day4, 55);
		Edge<Integer> edge35 = graph.insertEdge(day3, day5, 90);
		Edge<Integer> edge45 = graph.insertEdge(day4, day5, 50);
		assertEquals(10, graph.numEdges());
		assertNotNull(edge12);
		assertNotNull(edge13);
		assertNotNull(edge14);
		assertNotNull(edge15);
		assertNotNull(edge23);
		assertNotNull(edge24);
		assertNotNull(edge25);
		assertNotNull(edge34);
		assertNotNull(edge35);
		assertNotNull(edge45);
		
		
		//Tests the map made from the traversal
		Map<Vertex<String>, Edge<Integer>> map1 = GraphTraversalUtil.breadthFirstSearch(graph, day1);
		assertEquals(4, map1.size());
		assertEquals(edge12, map1.get(day2));
		assertEquals(edge13, map1.get(day3));
		assertEquals(edge14, map1.get(day4));
		assertEquals(edge15, map1.get(day5));
		
		Map<Vertex<String>, Edge<Integer>> map2 = GraphTraversalUtil.breadthFirstSearch(graph, day2);
		assertEquals(3, map2.size());
		assertEquals(edge23, map2.get(day3));
		assertEquals(edge24, map2.get(day4));
		assertEquals(edge25, map2.get(day5));
	}
}
