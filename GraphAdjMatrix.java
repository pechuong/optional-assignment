import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class GraphAdjMatrix implements Graph {

	public static class Edge {
		public int v1;
		public int v2;

		public Edge(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
		}
	}

	public int[][] adjMat;
	public HashSet<Integer> vertices;
	public TreeMap<Integer, ArrayList<Edge>> edges; 

	public GraphAdjMatrix(int vertices) {
		this.adjMat = new int[vertices][vertices];
		this.vertices = new HashSet<>();
		this.edges = new TreeMap<>();
	}

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

