package SPP;

public class dijkstra {

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
