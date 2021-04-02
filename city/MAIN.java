import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;
//  made by ohood alharbi -1706801
//Renad Bukhari- 1707458
//Luluah Aldakhil -1808720


// A Java program for Dijkstra's single source shortest path algorithm.
public class MAIN {
	static int numberOfCitys=12;
	static String[] CitysNames=new String[numberOfCitys];  // this to print number Of Cities
	static	long startTime = 0;
	static	long endTime_dj = 0;
	public static void main(String[] args) throws FileNotFoundException {




		Graph myGraph = new Graph(numberOfCitys);
		fillGraph(myGraph);
		CitysNames=myGraph.NodesNames;
//		myGraph.PrintAll();     //to ptint all cities
	myGraph.PrintAllToFile("output.txt");  // to print all edges in file

		dijkstra(myGraph, "Jeddah");
	}
 // this  function to find the vertex with minimum distance value

	private static void dijkstra(Graph graph, String src) {
		startTime = System.nanoTime();  // start time
		int nVertices = graph.numberOfNodes;

		// shortestDistances[i] will hold the
		// shortest distance from src to i
		int[] shortestDistances = new int[nVertices];

		// added[i] will true if vertex i is
		// included / in shortest path tree
		// or shortest distance from src to
		// i is finalized
		boolean[] added = new boolean[nVertices];

		// Initialize all distances as
		// INFINITE and added[] as false
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}

		// Distance of source vertex from
		// itself is always 0   =>  from Jeddah to Jeddah
		int startVertex = graph.findCityindex(src);
		shortestDistances[startVertex] = 0;

		// Parent array to store shortest
		// path tree
		int[] parents = new int[nVertices];

		// The starting vertex does not
		// have a parent
		parents[startVertex] = -1;

		// Find shortest path for all
		// vertices
		for (int i = 1; i < nVertices; i++) {

			// Pick the minimum distance vertex
			// from the set of vertices not yet
			// processed. nearestVertex is
			// always equal to startNode in
			// first iteration.
			int nearestVertex = -1;
			int shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

			// Mark the picked vertex as
			// processed
			added[nearestVertex] = true;

			// Update dist value of the
			// adjacent vertices of the
			// picked vertex.
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				int edgeDistance = graph.edgWeight(nearestVertex, vertexIndex);

				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
					parents[vertexIndex] = nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}

		printSolution(startVertex, shortestDistances, parents);
	}
// to print Solution
	private static void printSolution(int src, int[] distances, int[] parents) {
		System.out.println("|Start      |End     |Distance\t |Path");

		for (int i = 0; i < distances.length; i++) {
			if (i != src) {
				System.out.printf("|%-11s|%-8s|%-11d|",CitysNames[src],CitysNames[i],distances[i]);
				printPath(i, parents);
				endTime_dj = System.nanoTime();


				System.out.println();
			}

		}
		//calculate Time takes for  Algorithm
		//1. time takes by Dijkstra:
		long estmateTime_dj = endTime_dj - startTime;
		//System.out.println("estmateTime (millisecond)= " + ((double) estmateTime_Prim) / 1_000_000.0d);
		System.out.println("\n\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
				+ "\n--------- RunTime (milliSec) ---------"
				+ "\n>>_1_ Dijkstra algorithm = " + TimeUnit.NANOSECONDS.toMillis(estmateTime_dj)+" ms");
	}

	// Function to print shortest path
	// from source to currentVertex
	// using parents array
	private static void printPath(int currentVertex, int[] parents) {

		// Base case : Source node has
		// been processed
		if (currentVertex == -1) {
			return;
		}
		printPath(parents[currentVertex], parents);
		System.out.print(CitysNames[currentVertex] + " ");
	}

	public static int minDistance(int dist[], Boolean sptSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < dist.length; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}
//  this function to fill Graph
	private static void fillGraph(Graph myGraph) {
		// Graph myGraph=new;
		// Jeddah city
		myGraph.addEge("Jeddah", "Jeddah", 0);
		myGraph.addEge("Jeddah", "Makkah", 79);
		myGraph.addEge("Jeddah", "Madinah", 420);
		myGraph.addEge("Jeddah", "Riyadh", 949);
		myGraph.addEge("Jeddah", "Dammam", 1343);
		myGraph.addEge("Jeddah", "Taif", 167);
		myGraph.addEge("Jeddah", "Abha", 625);
		myGraph.addEge("Jeddah", "Tabuk", 1024);
		myGraph.addEge("Jeddah", "Qasim", 863);
		myGraph.addEge("Jeddah", "Hail", 777);
		myGraph.addEge("Jeddah", "Jizan", 710);
		myGraph.addEge("Jeddah", "Najran", 905);
		// Makkah city
		myGraph.addEge("Makkah", "Jeddah", 79);
		myGraph.addEge("Makkah", "Makkah", 0);
		myGraph.addEge("Makkah", "Madinah", 358);
		myGraph.addEge("Makkah", "Riyadh", 870);
		myGraph.addEge("Makkah", "Dammam", 1265);
		myGraph.addEge("Makkah", "Taif", 88);
		myGraph.addEge("Makkah", "Abha", 627);
		myGraph.addEge("Makkah", "Tabuk", 1037);
		myGraph.addEge("Makkah", "Qasim", 876);
		myGraph.addEge("Makkah", "Hail", 790);
		myGraph.addEge("Makkah", "Jizan", 685);
		myGraph.addEge("Makkah", "Najran", 912);
		// Madinah city
		myGraph.addEge("Madinah", "Jeddah", 420);
		myGraph.addEge("Madinah", "Makkah", 358);
		myGraph.addEge("Madinah", "Madinah", 0);
		myGraph.addEge("Madinah", "Riyadh", 848);
		myGraph.addEge("Madinah", "Dammam", 1343);
		myGraph.addEge("Madinah", "Taif", 446);
		myGraph.addEge("Madinah", "Abha", 985);
		myGraph.addEge("Madinah", "Tabuk", 679);
		myGraph.addEge("Madinah", "Qasim", 518);
		myGraph.addEge("Madinah", "Hail", 432);
		myGraph.addEge("Madinah", "Jizan", 1043);
		myGraph.addEge("Madinah", "Najran", 1270);
		// Riyadh city
		myGraph.addEge("Riyadh", "Jeddah", 949);
		myGraph.addEge("Riyadh", "Makkah", 870);
		myGraph.addEge("Riyadh", "Madinah", 848);
		myGraph.addEge("Riyadh", "Riyadh", 0);
		myGraph.addEge("Riyadh", "Dammam", 395);
		myGraph.addEge("Riyadh", "Taif", 782);
		myGraph.addEge("Riyadh", "Abha", 1064);
		myGraph.addEge("Riyadh", "Tabuk", 1304);
		myGraph.addEge("Riyadh", "Qasim", 330);
		myGraph.addEge("Riyadh", "Hail", 640);
		myGraph.addEge("Riyadh", "Jizan", 1272);
		myGraph.addEge("Riyadh", "Najran", 950);
		// Dammam city
		myGraph.addEge("Dammam", "Jeddah", 1343);
		myGraph.addEge("Dammam", "Makkah", 1265);
		myGraph.addEge("Dammam", "Madinah", 1343);
		myGraph.addEge("Dammam", "Riyadh", 395);
		myGraph.addEge("Dammam", "Dammam", 0);
		myGraph.addEge("Dammam", "Taif", 1177);
		myGraph.addEge("Dammam", "Abha", 1459);
		myGraph.addEge("Dammam", "Tabuk", 1729);
		myGraph.addEge("Dammam", "Qasim", 725);
		myGraph.addEge("Dammam", "Hail", 1035);
		myGraph.addEge("Dammam", "Jizan", 1667);
		myGraph.addEge("Dammam", "Najran", 1345);
		// Taif city
		myGraph.addEge("Taif", "Jeddah", 167);
		myGraph.addEge("Taif", "Makkah", 88);
		myGraph.addEge("Taif", "Madinah", 446);
		myGraph.addEge("Taif", "Riyadh", 782);
		myGraph.addEge("Taif", "Dammam", 1177);
		myGraph.addEge("Taif", "Taif", 0);
		myGraph.addEge("Taif", "Abha", 561);
		myGraph.addEge("Taif", "Tabuk", 1204);
		myGraph.addEge("Taif", "Qasim", 936);
		myGraph.addEge("Taif", "Hail", 957);
		myGraph.addEge("Taif", "Jizan", 763);
		myGraph.addEge("Taif", "Najran", 864);
		// Abha city
		myGraph.addEge("Abha", "Jeddah", 625);
		myGraph.addEge("Abha", "Makkah", 627);
		myGraph.addEge("Abha", "Madinah", 985);
		myGraph.addEge("Abha", "Riyadh", 1064);
		myGraph.addEge("Abha", "Dammam", 1495);
		myGraph.addEge("Abha", "Taif", 561);
		myGraph.addEge("Abha", "Abha", 0);
		myGraph.addEge("Abha", "Tabuk", 1649);
		myGraph.addEge("Abha", "Qasim", 1488);
		myGraph.addEge("Abha", "Hail", 1402);
		myGraph.addEge("Abha", "Jizan", 202);
		myGraph.addEge("Abha", "Najran", 280);
		// Tabuk city
		myGraph.addEge("Tabuk", "Jeddah", 1024);
		myGraph.addEge("Tabuk", "Makkah", 1037);
		myGraph.addEge("Tabuk", "Madinah", 679);
		myGraph.addEge("Tabuk", "Riyadh", 1304);
		myGraph.addEge("Tabuk", "Dammam", 1729);
		myGraph.addEge("Tabuk", "Taif", 1204);
		myGraph.addEge("Tabuk", "Abha", 1649);
		myGraph.addEge("Tabuk", "Tabuk", 0);
		myGraph.addEge("Tabuk", "Qasim", 974);
		myGraph.addEge("Tabuk", "Hail", 664);
		myGraph.addEge("Tabuk", "Jizan", 1722);
		myGraph.addEge("Tabuk", "Najran", 1929);
		// Qasim city
		myGraph.addEge("Qasim", "Jeddah", 863);
		myGraph.addEge("Qasim", "Makkah", 876);
		myGraph.addEge("Qasim", "Madinah", 518);
		myGraph.addEge("Qasim", "Riyadh", 330);
		myGraph.addEge("Qasim", "Dammam", 725);
		myGraph.addEge("Qasim", "Taif", 936);
		myGraph.addEge("Qasim", "Abha", 1488);
		myGraph.addEge("Qasim", "Tabuk", 974);
		myGraph.addEge("Qasim", "Qasim", 0);
		myGraph.addEge("Qasim", "Hail", 974);
		myGraph.addEge("Qasim", "Jizan", 1561);
		myGraph.addEge("Qasim", "Najran", 1280);
		// Hail city
		myGraph.addEge("Hail", "Jeddah", 777);
		myGraph.addEge("Hail", "Makkah", 790);
		myGraph.addEge("Hail", "Madinah", 432);
		myGraph.addEge("Hail", "Riyadh", 640);
		myGraph.addEge("Hail", "Dammam", 1035);
		myGraph.addEge("Hail", "Taif", 957);
		myGraph.addEge("Hail", "Abha", 1402);
		myGraph.addEge("Hail", "Tabuk", 664);
		myGraph.addEge("Hail", "Qasim", 310);
		myGraph.addEge("Hail", "Hail", 0);
		myGraph.addEge("Hail", "Jizan", 1475);
		myGraph.addEge("Hail", "Najran", 1590);

		// Jizan city
		myGraph.addEge("Jizan", "Jeddah", 710);
		myGraph.addEge("Jizan", "Makkah", 685);
		myGraph.addEge("Jizan", "Madinah", 1043);
		myGraph.addEge("Jizan", "Riyadh", 1272);
		myGraph.addEge("Jizan", "Dammam", 1667);
		myGraph.addEge("Jizan", "Taif", 763);
		myGraph.addEge("Jizan", "Abha", 202);
		myGraph.addEge("Jizan", "Tabuk", 1722);
		myGraph.addEge("Jizan", "Qasim", 1561);
		myGraph.addEge("Jizan", "Hail", 1475);
		myGraph.addEge("Jizan", "Jizan", 0);
		myGraph.addEge("Jizan", "Najran", 482);
		// Najran city
		myGraph.addEge("Najran", "Jeddah", 905);
		myGraph.addEge("Najran", "Makkah", 912);
		myGraph.addEge("Najran", "Madinah", 1270);
		myGraph.addEge("Najran", "Riyadh", 950);
		myGraph.addEge("Najran", "Dammam", 1345);
		myGraph.addEge("Najran", "Taif", 864);
		myGraph.addEge("Najran", "Abha", 280);
		myGraph.addEge("Najran", "Tabuk", 1929);
		myGraph.addEge("Najran", "Qasim", 1280);
		myGraph.addEge("Najran", "Hail", 1590);
		myGraph.addEge("Najran", "Jizan", 482);
		myGraph.addEge("Najran", "Najran", 0);

	}

}
