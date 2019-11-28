import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 找出无向图中所有的连通块。
 * 图中的每个节点包含一个label属性和一个邻接点的列表。
 * （一个无向图的连通块是一个子图，其中任意两个顶点通过路径相连，且不与整个图中的其它顶点相连。）
 * 你需要返回 label 集合的列表.
 */

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}

public class ConnectedComponentsInUndirectedGraph {

  public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {

		HashMap<UndirectedGraphNode, Boolean> nodeToVisited = new HashMap<>();

		for(UndirectedGraphNode node : nodes) 
			nodeToVisited.put(node, false);
		
		List<List<Integer>> results = new ArrayList<>();
		
		for (UndirectedGraphNode node : nodes)
			if (!nodeToVisited.get(node)) 
				results.add(bfs(node, nodeToVisited));
		
		return results;
	}

	private List<Integer> bfs(UndirectedGraphNode node, HashMap<UndirectedGraphNode, Boolean> nodeToVisited) {
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		queue.offer(node);
		nodeToVisited.put(node, true);

		List<Integer> graph = new ArrayList<>();
		while (!queue.isEmpty()) {
			UndirectedGraphNode tmp = queue.poll();
			graph.add(tmp.label);

			for (UndirectedGraphNode neighbor : tmp.neighbors) {
				if (nodeToVisited.get(neighbor)) continue;
				queue.offer(neighbor);
				nodeToVisited.put(neighbor, true);
			}
		}
		Collections.sort(graph);
		return graph;
	}
}
