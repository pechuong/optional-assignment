import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class GraphAdjMatrix implements Graph {

	/** 
	 * Edge class to keep track of which two vertices
	 * form an edge
	 */
	public static class Edge {
		public int v1; /* The first vertex  */
		public int v2; /* The second vertex */

		/**
		 * Initializes the Edge
		 */
		public Edge(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
		}
	}

	public int[][] adjMat;    			/* 		The Adjacency Matrix 				*/
	public HashSet<Integer> vertices; 		/*	 Contains all the vertices in the matrix 		*/
	public TreeMap<Integer, ArrayList<Edge>> edges; /* Keeps track of each edge and the vertices it's between 	*/

	/**
	 * Initializes the Adjacency matrix and sets / maps 
	 * to keep track of vertices and edges
	 */
	public GraphAdjMatrix(int vertices) {
		this.adjMat = new int[vertices][vertices];
		this.vertices = new HashSet<>();
		this.edges = new TreeMap<>();
	}

	/**
	 * Adds an edge of some weight between two vertices in the adjacency matrix
	 *
	 * @param v1 The first vertex
	 * @param v2 The second vertex
	 * @param weight The weight between the two vertices
	 */
	public void addEdge(int v1, int v2, int weight) {
		adjMat[v1][v2] = weight;
		adjMat[v2][v1] = weight;
		vertices.add(v1);
		vertices.add(v2);
		if (edges.containsKey(weight)) {
			edges.get(weight).add(new Edge(v1, v2));
		} else {
			ArrayList<Edge> temp = new ArrayList<Edge>();
			temp.add(new Edge(v1, v2));
			edges.put(weight, temp);
		}
	}

	/**
	 * Gets the weight of the edge vertices
	 *
	 * @param v1 The first vertex
	 * @param v2 The second vertex
	 * @return int The weight of the edge between the two vertices
	 */
	public int getEdge(int v1, int v2) {
		return adjMat[v1][v2];
	}

	/**
	 * Creates a minimum spanning tree from the source adj matrix
	 * 
	 * @return The weight of the minimum tree
	 */
	public int createSpanningTree() {
		int totalWeight = 0;
		int[][] minTree = new int[vertices.size()][vertices.size()];
		HashSet<Integer> visited = new HashSet<>();
		for (int weight : edges.keySet()) {
			for (Edge edge : edges.get(weight)) {
				// For my edge, if both vertex have been visited or is in the network already
				// Then I know that I'm creating a cycle.
				if (!visited.contains(edge.v1) || !visited.contains(edge.v2)) {
					minTree[edge.v1][edge.v2] = weight;
					minTree[edge.v2][edge.v1] = weight;
					visited.add(edge.v1);
					visited.add(edge.v2);
					totalWeight += weight;
				}
			}	
		}
		adjMat = minTree;
		return totalWeight;
	}

}	

