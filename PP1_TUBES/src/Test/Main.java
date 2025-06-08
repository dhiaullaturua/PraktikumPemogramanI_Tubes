package Test;

public class Main {
	
	    public static void main(String[] args) {
	        Graph g = new Graph();

	        g.addVertex("A");
	        g.addVertex("B");
	        g.addVertex("C");
	        g.addVertex("D");

	        g.addEdge("A", "B", 1);
	        g.addEdge("A", "C", 4);
	        g.addEdge("B", "C", 2);
	        g.addEdge("C", "D", 1);
	        g.addEdge("B", "D", 5);

	        g.display();              // Menampilkan adjacency list
	        g.dijkstra("A");         // Menjalankan algoritma Dijkstra dari node A
	    }
	}
