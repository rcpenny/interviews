import java.awt.ContainerOrderFocusTraversalPolicy;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 六度分离是一个哲学问题，说的是每个人每个东西可以通过六步或者更少的步数建立联系。
 * 现在给你一个友谊关系，查询两个人可以通过几步相连，如果不相连返回 -1
 */
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}

public class SixDegrees {
  public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
		if (s == null || t == null) return -1;

		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		Set<Integer> visisted = new HashSet<>();
		queue.offer(s);
		visisted.add(s.label);

		int steps = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				UndirectedGraphNode cur = queue.poll();
				if (cur.label == t.label) return steps;
				for (UndirectedGraphNode neighbor : cur.neighbors) {
					if (visisted.contains(neighbor.label)) continue;
					queue.offer(neighbor);
					visisted.add(neighbor.label);
				}
			}
			steps++;
		}

		return -1;
	}
}
