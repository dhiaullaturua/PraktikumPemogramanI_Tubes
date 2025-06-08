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

    public void dijkstraWithSchedule(String startName) {
        for (Vertex v : vertices.values()) {
            v.visited = false;
        }

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
            if (current.visited) continue;
            current.visited = true;

            for (Edge edge : adjacencyList.get(current)) {
                Vertex neighbor = edge.to;
                int newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(neighbor);
                }
            }
        }

        // Tampilkan Jadwal Pengiriman
        System.out.println("\n📦 Jadwal Pengiriman dari " + startName + ":");
        int waktuMulai = 8 * 60; // jam 08:00 pagi dalam menit
        for (Vertex v : distances.keySet()) {
            if (v != start && distances.get(v) < Integer.MAX_VALUE) {
                int waktuTiba = waktuMulai + distances.get(v) * 2; // anggap 1 km = 2 menit
                int jam = waktuTiba / 60;
                int menit = waktuTiba % 60;
                System.out.printf("- %s tiba pukul %02d:%02d (jarak %d km)\n", v.name, jam, menit, distances.get(v));
            }
        }
    }

}
