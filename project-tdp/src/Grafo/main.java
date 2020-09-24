package Grafo;

public class main {

	public static void main(String [] args) {
		
		Grafo<Integer> grafo = new Grafo<Integer>();
		
		//addNode
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		grafo.addNode(4);
		grafo.addNode(5);
		grafo.addNode(1);
		
		System.out.println("");
		
		//addEdge
		grafo.addEdge(5,6);
		grafo.addEdge(6,5);
		grafo.addEdge(1,2);
		grafo.addEdge(1,2);
		grafo.addEdge(2,3);
		grafo.addEdge(1,3);
		grafo.addEdge(2,4);
		grafo.addEdge(2,5);
		
		System.out.println("");
		
		//removeNode
		grafo.removeNode(5);
		grafo.removeNode(7);
		
		System.out.println("");
		
		//removeEdge
		grafo.removeEdge(6,6);
		grafo.removeEdge(4,7);
		grafo.removeEdge(7,4);
		grafo.removeEdge(1,2);
	}
}
