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
		Map<UndirectedGraphNode, Integer> values, UndirectedGraphNode start, int target) {

		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		Set<UndirectedGraphNode> seen = new HashSet<UndirectedGraphNode>();
		
		queue.offer(start);
		seen.add(start);

		while (!queue.isEmpty()) {
			UndirectedGraphNode head = queue.poll();
			if (values.get(head) == target) return head;

			for (UndirectedGraphNode nei : head.neighbors) {
				if (seen.contains(nei)) continue;
				queue.offer(nei);
				seen.add(nei);
			}
		}
		return null;
	}

}
