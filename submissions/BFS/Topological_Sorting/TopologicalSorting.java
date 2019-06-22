/**
 *
 */

class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;
	DirectedGraphNode(int x) {
		label = x; 
		neighbors = new ArrayList<DirectedGraphNode>();
	}
};

final class TopologicalSorting {

  public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		// caculate in-degrees of all nodes
	}
}
