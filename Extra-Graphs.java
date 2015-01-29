//Little breadth-first search example in graphs

package Exercise11;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import Exercise05.DecoratedElementTraversal;
import graphsDSESIUCLM.*;

@SuppressWarnings("rawtypes")
public class CheckConnection {

	public static void main(String[] args) {

		Customer paco = new Customer("Paco", "A"), ramon = new Customer("Ramon", "F");
		Graph grafo = new TreeMapGraph<>();
		llenarGrafo(grafo);

		String msg = checkConn(paco, ramon, grafo);

		System.out.println(msg);

	}

	public static String checkConn(Customer a, Customer b, Graph gr){
		Vertex<DecoratedElementTraversal> 	vertexA = gr.getVertex(a.getStation()), 
											vertexB = gr.getVertex(b.getStation());

		if( vertexA == null || vertexB == null) return "invalid client";

		int path_length = BFwithDistance(vertexA, vertexB, gr);

		switch(path_length){
		case -1: return "impossible to stablish connection";
		case 0:  return "fucking awesome fucking conection mdfk u guys r lucky guys hu";
		case 1:
		case 2:
		case 3:
			return "good conection ("+path_length+")";
		case 4:
			return "poor conection ("+path_length+")";
		default:
			return "too far to stablish conection ("+path_length+")";
		}
	}

	
	private static int BFwithDistance(Vertex<DecoratedElementTraversal> vertexA, Vertex<DecoratedElementTraversal> vertexB, Graph grafo) {
		if (vertexA.getElement().equals(vertexB.getElement())) return 0;

		Vertex<DecoratedElementTraversal> vertexaux, vertexaux2;
		Edge<Vertex<DecoratedElementTraversal>> edgeaux;
		int distance = -1;
		boolean iFoundIt = false;
		Queue<Vertex<DecoratedElementTraversal>> queue = new LinkedList<Vertex<DecoratedElementTraversal>>();

		//reiniciamos todos los valores
		Iterator<Vertex<DecoratedElementTraversal>> iterV = grafo.getVertices();
		Iterator<Edge<DecoratedElementTraversal>> iterE;
		while(iterV.hasNext()){ //reiniciamos todos los valores
			vertexaux = iterV.next();
			vertexaux.getElement().setVisited(false);
			vertexaux.getElement().setDistance(0);
		}

		vertexA.getElement().setVisited(true); //empezamos con el primer vertice
		queue.offer(vertexA);

		while(!queue.isEmpty() && !iFoundIt){
			vertexaux = queue.remove();
			iterE = grafo.incidentEdges(vertexaux);
			
			while(iterE.hasNext() && !iFoundIt){
				vertexaux2 = grafo.opposite(vertexaux, iterE.next());
				
				if(!vertexaux2.getElement().getVisited()){
					vertexaux2.getElement().setVisited(true);
					
					vertexaux2.getElement().setDistance(vertexaux.getElement().getDistance() + 1);
					
					if( vertexaux2.getElement().equals(vertexB.getElement()) ) 
						iFoundIt= true;
					else
						queue.offer(vertexaux2);
				}
			}				
		}
		//si vertexaux2 equals vertexB entonces son el mismo vertice, asique lo usamos		
		if(iFoundIt) distance = vertexB.getElement().getDistance();
		else distance = -1;

		return distance;
	}


	private static void llenarGrafo(Graph gr) {
		DecoratedElementTraversal e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11;
		e1 = new DecoratedElementTraversal("A", 1); //key, element
		e2 = new DecoratedElementTraversal("B", 2);
		e3 = new DecoratedElementTraversal("C", 3);
		e4 = new DecoratedElementTraversal("D", 4);
		e5 = new DecoratedElementTraversal("E", 5);
		e6 = new DecoratedElementTraversal("F", 6);
		e7 = new DecoratedElementTraversal("G", 7);
		e8 = new DecoratedElementTraversal("H", 8);
		e9 = new DecoratedElementTraversal("I", 9);
		e10 = new DecoratedElementTraversal("J", 10);
		e11 = new DecoratedElementTraversal("K", 11);

		gr.insertEdge(e1, e2);
		gr.insertEdge(e1, e3);
		gr.insertEdge(e1, e4);
		gr.insertEdge(e2, e3);
		gr.insertEdge(e2, e5);
		gr.insertEdge(e2, e7);
		gr.insertEdge(e3, e4);
		gr.insertEdge(e3, e5);
		gr.insertEdge(e3, e6);
		gr.insertEdge(e4, e6);
		gr.insertEdge(e5, e6);
		gr.insertEdge(e5, e7);
		gr.insertEdge(e7, e8);
		gr.insertEdge(e7, e9);
		gr.insertEdge(e8, e9);
		gr.insertEdge(e8, e11);
		gr.insertEdge(e9, e10);
		gr.insertEdge(e10, e11);
	}

}
