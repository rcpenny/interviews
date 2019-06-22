import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** https://www.lintcode.com/problem/topological-sorting/ */

class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;
	DirectedGraphNode(int x) {
		label = x; 
		neighbors = new ArrayList<DirectedGraphNode>();
	}
};

public class TopologicalSorting {

  public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		ArrayList<DirectedGraphNode> results = new ArrayList<>();
		if (graph == null || graph.size() == 0) return results;

		// 统计所有点的入度
		Map<DirectedGraphNode, Integer> NodeToIndegrees = new HashMap<>();
		for (DirectedGraphNode node : graph) {
			for (DirectedGraphNode neighbor : node.neighbors) {
				NodeToIndegrees.putIfAbsent(node, 0);
				int indgree = NodeToIndegrees.getOrDefault(neighbor, 0);
				NodeToIndegrees.put(neighbor, indgree + 1);
			}
		}

		// 将所有入度为 0 的点，也就是那些没有任何依赖的点，放到宽度优先搜索的队列中
		Queue<DirectedGraphNode> queue = new LinkedList<>();
		for (DirectedGraphNode node : graph) {
			if (NodeToIndegrees.get(node) == 0) {
				queue.offer(node);
				results.add(node);
			}
		}

		// 每次从队列中拿出一个点放到拓扑序列里，并将该点指向的所有点的入度减1
		// 如果发现某个点的入度被减去 1 之后变成了 0，则放入队列中。
		while (!queue.isEmpty()) {
			DirectedGraphNode tmp = queue.poll();
			for (DirectedGraphNode neighbor : tmp.neighbors) {
				int degrees = NodeToIndegrees.get(neighbor);
				NodeToIndegrees.put(neighbor, degrees - 1);
				if (NodeToIndegrees.get(neighbor) == 0) {
					queue.offer(neighbor);
					results.add(neighbor);
				}
			}
		}
		return results;
	}
}
