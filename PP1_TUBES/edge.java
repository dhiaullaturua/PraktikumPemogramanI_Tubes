package SPP;

public class Edge {
	
	Vertex from;
    Vertex to;
    int weight; // bisa dianggap sebagai jarak (km)

    public Edge(Vertex from, Vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}
