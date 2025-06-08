package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Test.Vertex;
import Test.Edge;

public class Graph {
	

	
	private Map<String, Vertex> vertices = new HashMap<>();
    private Map<Vertex, List<Edge>> adjacencyList = new HashMap<>();

    void addVertex(String name) {
        Vertex v = new Vertex(name);
        vertices.put(name, v);
        adjacencyList.put(v, new ArrayList<>());
    }

    void addEdge(String from, String to, int weight) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);
        if (v1 != null && v2 != null) {
            adjacencyList.get(v1).add(new Edge(v1, v2, weight));
        }
    }

    // Menampilkan adjacency list
    void display() {
        for (Vertex v : adjacencyList.keySet()) {
            System.out.print(v.name + " -> ");
            for (Edge e : adjacencyList.get(v)) {
                System.out.print(e.to.name + "(" + e.weight + ") ");
            }
            System.out.println();
        }
    }

    // Algoritma Dijkstra untuk mencari jalur terpendek
    void dijkstra(String startName) {
        Vertex start = vertices.get(startName);
        Map<Vertex, Integer> distances = new HashMap<>();
        for (Vertex v : vertices.values()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            current.visited = true;

            for (Edge edge : adjacencyList.get(current)) {
                Vertex neighbor = edge.to;
                if (!neighbor.visited) {
                    int newDist = distances.get(current) + edge.weight;
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        pq.add(neighbor);
                    }
                }
            }
        }

        // Tampilkan hasil
        System.out.println("Jarak terpendek dari " + startName + ":");
        for (Vertex v : distances.keySet()) {
            System.out.println(startName + " -> " + v.name + " = " + distances.get(v));
        }
    }
}

