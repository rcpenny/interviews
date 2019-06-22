import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 能够用 BFS 解决的问题，一定不要用 DFS 去做！ 
 * 因为用 Recursion 实现的 DFS 可能造成 StackOverflow
 */
public class BFS {

	public void bfs() {
		Queue<T> queue = new LinkedList<>();
		Set<T> set = new HashSet<>();

		set.add(start);
		queue.offer(start);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				T head = queue.poll();
				for (T neighbor : head.neighbors) {
					if (set.contains(neighbor)) continue;
					set.add(neighbor);
					queue.offer(neighbor);
				}
			}
		}
	}
}
