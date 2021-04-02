
public class Edge {
	int weight;
	String i;   //number of vertex i(source)
	String j;  //number of vertex j(destination)

	//determined info of Edge : source vertex i, destenation vertex j , weight bw them
	public Edge(String i, String j, int weight) {
		this.weight = weight;
		this.i = i;
		this.j = j;
	}
	//determined info of Edge : source vertex i, destenation vertex j
	public Edge(String i, String j) {
		this.i = i;
		this.j = j;
	}
	//check main obj ==> source i , destinationj
	public boolean equals(Object o) {
		//related to contains (in exist method)
		return (this.i.equalsIgnoreCase(((Edge) o).i) && this.j.equalsIgnoreCase(((Edge) o).j));

	}




	@Override
	public String toString() {
		return "(" + i + "," + j + ")->" + weight;
	}

}
