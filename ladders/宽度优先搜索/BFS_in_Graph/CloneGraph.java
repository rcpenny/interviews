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
}

public class CloneGraph {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return node;

		// 1. bfs找到所有点
		ArrayList<UndirectedGraphNode> nodes = getNodes(node);

		// 2. 深度拷贝一个 old-node -> new-node 的哈希表
		HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
		for (UndirectedGraphNode n : nodes)
			mapping.put(n, new UndirectedGraphNode(n.label));

		// 3. 根据old-nodes，用new-nodes重构新图
		for (UndirectedGraphNode old : nodes) {
			UndirectedGraphNode newNode = mapping.get(old);
			for (UndirectedGraphNode neighbor : old.neighbors) {
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
		return new ArrayList<>(set);
	}
}
