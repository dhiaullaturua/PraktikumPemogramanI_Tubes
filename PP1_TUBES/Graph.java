package SPP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import SPP.Vertex;
import SPP.Edge;

public class Graph {
	
	private Map<String, Vertex> vertices = new HashMap<>();
    private Map<Vertex, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(String name) {
        Vertex v = new Vertex(name);
        vertices.put(name, v);
        adjacencyList.put(v, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);
        if (v1 != null && v2 != null) {
            adjacencyList.get(v1).add(new Edge(v1, v2, weight));
            adjacencyList.get(v2).add(new Edge(v2, v1, weight)); // karena graf undirected
        }
    }

    public void display() {
        for (Vertex v : adjacencyList.keySet()) {
            System.out.print(v.name + " -> ");
            for (Edge e : adjacencyList.get(v)) {
                System.out.print(e.to.name + "(" + e.weight + ") ");
            }
            System.out.println();
        }
    }
}
