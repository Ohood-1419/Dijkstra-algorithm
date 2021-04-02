import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	int numberOfNodes;
	ArrayList<Edge>[] Nodes;
	String[] NodesNames;  // i use this to insert name of nodes



	public Graph(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
		this.Nodes = new ArrayList[numberOfNodes];
		NodesNames = new String[numberOfNodes]; // initialize array to save the name's of cities
		// initialize all nodes
		for (int i = 0; i < Nodes.length; i++) {
			Nodes[i] = new ArrayList<Edge>();
		}
	}
//the goal of this method is find the Weight of edge
	public int edgWeight(int i, int j) {
		Edge searchEdge = new Edge(NodesNames[i], NodesNames[j]);
		// find the edge of the iIndex city with the jth city
		int targetIndex = Nodes[i].indexOf(searchEdge);
		// if the target index is -1 then there is no edge between them
		if (targetIndex != -1)
			return Nodes[i].get(targetIndex).weight;
		else
			return 0;

	}
	//the goal of this method is add all Edges
	public void addEge(String i, String j, int weight) {
		Edge newEdge = new Edge(i, j, weight);

		int index = findCityindex(i);
		if (index == -1) {
			index = findFreeNode();

			if (index == -1)
				return;
			NodesNames[index] = i;
		}
		Nodes[index].add(newEdge);
	}
// this method to find FreeNode
	private int findFreeNode() {
		for (int i = 0; i < NodesNames.length; i++) {
			if (NodesNames[i] == null) {
				return i;
			}
		}
		return -1;
	}
	// this method to display  Node Edges
	public void printNodeEdges(int node) {
		if (Nodes[node].size() == 0) {
			System.out.println("This node dose not have any edges yet");
		} else {
			for (int i = 0; i < Nodes[node].size(); i++) {
				System.out.println(Nodes[node].get(i));
			}
		}
	}
	// this method to all edges
	public void PrintAll() {
		for (int node = 0; node < Nodes.length; node++) {
			System.out
					.println("=========================== Node(" + NodesNames[node] + ") ===========================");
			printNodeEdges(node);
		}
	}
	// this method on file
	public void PrintAllToFile(String fileName) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(fileName);
		for (int node = 0; node < Nodes.length; node++) {
			output.println("=========================== Node(" + NodesNames[node] + ") ===========================");
			if (Nodes[node].size() == 0) {
				output.println("This node dose not have any edges yet");
			} else {
				for (int i = 0; i < Nodes[node].size(); i++) {
					output.println(Nodes[node].get(i));
				}
			}
		}
		output.flush();
		output.close();

	}
	// this method find City index
	public int findCityindex(String city) {
		for (int i = 0; i < NodesNames.length; i++) {
			if (city.equalsIgnoreCase(NodesNames[i]))
				return i;
		}
		return -1;
	}
}