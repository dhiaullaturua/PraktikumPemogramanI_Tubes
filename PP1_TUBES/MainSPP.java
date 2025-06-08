package SPP;

public class MainSPP {
	
	public static void main(String[] args) {
        Graph g = new Graph();

        g.addVertex("A"); // Gudang
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");

        // Tambahkan jalur (jarak antar kota)
        g.addEdge("A", "B", 5);
        g.addEdge("A", "C", 10);
        g.addEdge("A", "D", 8);
        g.addEdge("A", "E", 7);
        g.addEdge("B", "C", 3);
        g.addEdge("B", "D", 6);
        g.addEdge("B", "E", 4);
        g.addEdge("C", "D", 2);
        g.addEdge("C", "E", 5);
        g.addEdge("D", "E", 3);

        g.display(); // Tampilkan struktur graph

        g.dijkstraWithSchedule("A"); // Hitung dan tampilkan jadwal pengiriman
    }

}