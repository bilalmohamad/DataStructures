package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Tests the EdgeListGraph class
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class EdgeListGraphTest {

	
	/**
	 * Tests the EdgeListGraph constructor with nothing entered in its parameter
	 */
	@Test
	public void testEmptyConstructor() {
		
		//Undirected graph
		AbstractGraph<String, Integer> graph = new EdgeListGraph<String, Integer>();
		assertEquals(false, graph.isDirected());			
	}
	
	
	/**
	 * Tests the EdgeListGraph class's methods with true entered in the constructor parameter
	 */
	@Test
	public void testEdgeListGraph() {
		
		//Directed graph
		AbstractGraph<String, Integer> graph = new EdgeListGraph<String, Integer>(true);
		assertEquals(true, graph.isDirected());
		assertEquals(0, graph.numVertices());
		assertEquals(0, graph.numEdges());
		
		
		//Tests insertVertex
		Vertex<String> day1 = graph.insertVertex("Day 1");
		Vertex<String> day2 = graph.insertVertex("Day 2");
		Vertex<String> day3 = graph.insertVertex("Day 3");
		Vertex<String> day4 = graph.insertVertex("Day 4");
		Vertex<String> day5 = graph.insertVertex("Day 5");
		assertEquals(5, graph.numVertices());
		
		
		//Tests getElement for Vertices
		assertEquals("Day 1", day1.getElement());
		assertEquals("Day 2", day2.getElement());
		assertEquals("Day 3", day3.getElement());
		assertEquals("Day 4", day4.getElement());
		assertEquals("Day 5", day5.getElement());
		
		//Tests vertices
		Iterator<Vertex<String>> vertIt = graph.vertices().iterator();
		assertEquals(day1, vertIt.next());
		assertEquals(day2, vertIt.next());
		assertEquals(day3, vertIt.next());
		assertEquals(day4, vertIt.next());
		assertEquals(day5, vertIt.next());
		assertEquals(null, vertIt.next());
		
		//Tests insertEdge
		Edge<Integer> edge12 = graph.insertEdge(day1, day2, 85);
		Edge<Integer> edge13 = graph.insertEdge(day1, day3, 180);
		Edge<Integer> edge14 = graph.insertEdge(day1, day4, 255);
		Edge<Integer> edge15 = graph.insertEdge(day1, day5, 500);
		assertEquals(4, graph.numEdges());
		
		Edge<Integer> edge23 = graph.insertEdge(day2, day3, 65);
		Edge<Integer> edge24 = graph.insertEdge(day2, day4, 90);
		Edge<Integer> edge25 = graph.insertEdge(day2, day5, 220);
		assertEquals(7, graph.numEdges());
		
		Edge<Integer> edge34 = graph.insertEdge(day3, day4, 55);
		Edge<Integer> edge35 = graph.insertEdge(day3, day5, 90);
		assertEquals(9, graph.numEdges());
		
		Edge<Integer> edge45 = graph.insertEdge(day4, day5, 50);
		assertEquals(10, graph.numEdges());
		
		
		//Tests getElement for Edges
		assertEquals(85, edge12.getElement(), 0.001);
		assertEquals(180, edge13.getElement(), 0.001);
		assertEquals(255, edge14.getElement(), 0.001);
		assertEquals(500, edge15.getElement(), 0.001);
		assertEquals(65, edge23.getElement(), 0.001);
		assertEquals(90, edge24.getElement(), 0.001);
		assertEquals(220, edge25.getElement(), 0.001);
		assertEquals(55, edge34.getElement(), 0.001);
		assertEquals(90, edge35.getElement(), 0.001);
		assertEquals(50, edge45.getElement(), 0.001);
		
		
		//Tests toString for Edges
		assertEquals("Edge[element=85]", edge12.toString());
		assertEquals("Edge[element=180]", edge13.toString());
		assertEquals("Edge[element=255]", edge14.toString());
		assertEquals("Edge[element=500]", edge15.toString());
		assertEquals("Edge[element=65]", edge23.toString());
		assertEquals("Edge[element=90]", edge24.toString());
		assertEquals("Edge[element=220]", edge25.toString());
		assertEquals("Edge[element=55]", edge34.toString());
		assertEquals("Edge[element=90]", edge35.toString());
		assertEquals("Edge[element=50]", edge45.toString());
		
		
		//Tests edges
		Iterator<Edge<Integer>> edgeIt = graph.edges().iterator();
		assertEquals(edge12, edgeIt.next());
		assertEquals(edge13, edgeIt.next());
		assertEquals(edge14, edgeIt.next());
		assertEquals(edge15, edgeIt.next());
		assertEquals(edge23, edgeIt.next());
		assertEquals(edge24, edgeIt.next());
		assertEquals(edge25, edgeIt.next());
		assertEquals(edge34, edgeIt.next());
		assertEquals(edge35, edgeIt.next());
		assertEquals(edge45, edgeIt.next());
		assertEquals(null, edgeIt.next());
		
		
		//Tests getEdge
		assertEquals(edge12, graph.getEdge(day1, day2));
		assertEquals(edge13, graph.getEdge(day1, day3));
		assertEquals(edge34, graph.getEdge(day3, day4));
		assertEquals(null, graph.getEdge(day4, day3));
		
		
		//Tests endVertices
		Vertex<String>[] end12 = graph.endVertices(edge12);
		assertEquals(day1, end12[0]);
		assertEquals(day2, end12[1]);
		
		
		//Tests opposite
		assertEquals(day2, graph.opposite(day1, edge12));
		assertEquals(day1, graph.opposite(day2, edge12));
		assertEquals(day3, graph.opposite(day5, edge35));
		assertEquals(day5, graph.opposite(day3, edge35));
		
		
		//Tests outDegree
		assertEquals(4, graph.outDegree(day1));
		assertEquals(3, graph.outDegree(day2));
		assertEquals(2, graph.outDegree(day3));
		assertEquals(1, graph.outDegree(day4));
		assertEquals(0, graph.outDegree(day5));
		
		
		//Tests inDegree
		assertEquals(0, graph.inDegree(day1));
		assertEquals(1, graph.inDegree(day2));
		assertEquals(2, graph.inDegree(day3));
		assertEquals(3, graph.inDegree(day4));
		assertEquals(4, graph.inDegree(day5));
		
		
		//Tests outgoingEdges
		Iterator<Edge<Integer>> outIt = graph.outgoingEdges(day1).iterator();
		assertEquals(edge12, outIt.next());
		assertEquals(edge13, outIt.next());
		assertEquals(edge14, outIt.next());
		assertEquals(edge15, outIt.next());
		assertEquals(false, outIt.hasNext());
		
		Iterator<Edge<Integer>> outIt2 = graph.outgoingEdges(day5).iterator();
		assertEquals(false, outIt2.hasNext());
		
		
		//Tests incomingEdges
		Iterator<Edge<Integer>> inIt = graph.incomingEdges(day5).iterator();
		assertEquals(edge15, inIt.next());
		assertEquals(edge25, inIt.next());
		assertEquals(edge35, inIt.next());
		assertEquals(edge45, inIt.next());
		assertEquals(false, inIt.hasNext());
		
		Iterator<Edge<Integer>> inIt2 = graph.incomingEdges(day1).iterator();
		assertEquals(false, inIt2.hasNext());
		
		
		//Tests removeVertex and removeEdge
		assertEquals(day1, graph.removeVertex(day1));
		assertEquals(6, graph.numEdges());
		assertEquals(4, graph.numVertices());
		
		assertEquals(edge23, graph.removeEdge(edge23));
		assertEquals(5, graph.numEdges());
		assertEquals(edge24, graph.removeEdge(edge24));
		assertEquals(4, graph.numEdges());
		assertEquals(edge25, graph.removeEdge(edge25));
		assertEquals(3, graph.numEdges());
		
		assertEquals(day2, graph.removeVertex(day2));
		assertEquals(3, graph.numEdges());
		assertEquals(3, graph.numVertices());
		
		assertEquals(day4, graph.removeVertex(day4));
		assertEquals(1, graph.numEdges());
		assertEquals(2, graph.numVertices());
		
		assertEquals(day3, graph.removeVertex(day3));
		assertEquals(0, graph.numEdges());
		assertEquals(1, graph.numVertices());
		
		assertEquals(day5, graph.removeVertex(day5));
		assertEquals(0, graph.numEdges());
		assertEquals(0, graph.numVertices());
	}
}
