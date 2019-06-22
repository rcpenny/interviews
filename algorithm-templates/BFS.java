import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
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
