/**
 * 克隆一张无向图. 无向图的每个节点包含一个 label 和一个列表 neighbors. 保证每个节点的 label 互不相同.
 * 你的程序需要返回一个经过深度拷贝的新图. 新图和原图具有同样的结构, 并且对新图的任何改动不会对原图造成任何影响.
 */

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
};

public class CloneGraph {

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return node;

		// 用bfs找到所有点
		ArrayList<UndirectedGraphNode> nodes = getNodes(node);

		// 深度拷贝一个 key - value old_node -> new -> node
		HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
		for (UndirectedGraphNode n : nodes) {
				mapping.put(n, new UndirectedGraphNode(n.label));
		}

		// 用新node作为新node的邻居
		for (UndirectedGraphNode n : nodes) {
				UndirectedGraphNode newNode = mapping.get(n);
				for (UndirectedGraphNode neighbor : n.neighbors) {
						UndirectedGraphNode newNeighbor = mapping.get(neighbor);
						newNode.neighbors.add(newNeighbor);
				}
		}

		return mapping.get(node);
}

private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashSet<UndirectedGraphNode> set = new HashSet<>();

		queue.offer(node);
		set.add(node);
		while (!queue.isEmpty()) {
				UndirectedGraphNode head = queue.poll();
				for (UndirectedGraphNode neighbor : head.neighbors) {
						if (!set.contains(neighbor)) {
								set.add(neighbor);
								queue.offer(neighbor);
						}
				}
		}
		return new ArrayList<UndirectedGraphNode>(set);
	}
}
