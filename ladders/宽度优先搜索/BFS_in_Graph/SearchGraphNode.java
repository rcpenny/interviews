/**
 * 给定一张 无向图，一个 节点 以及一个 目标值，返回距离这个节点最近 且 值为目标值的节点，
 * 如果不能找到则返回 NULL
 */
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}

public class SearchGraphNode {

	public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph, 
		Map<UndirectedGraphNode, Integer> values, UndirectedGraphNode node, int target) {

		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		Set<UndirectedGraphNode> visisted = new HashSet<UndirectedGraphNode>();
		queue.offer(node);
		visisted.add(node);

		while (!queue.isEmpty()) {
			UndirectedGraphNode head = queue.poll();
			if (values.get(head) == target) return head;

			for (UndirectedGraphNode nei : head.neighbors) {
				if (visisted.contains(nei)) continue;
				queue.offer(nei);
				visisted.add(nei);
			}
		}
		return null;
	}

}
