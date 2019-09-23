import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 问是否存在拓扑序（是否可以被拓扑排序）
 * 
 * 现在你总共有 n 门课需要选，记为 0 到 n - 1.
 * 一些课程在修之前需要先修另外的一些课程，
 * 比如要学习课程 0 你需要先学习课程 1 ，表示为[0,1]
 * 给定n门课以及他们的先决条件，判断是否可能完成所有课程？
 */
public class CourseSchedule {

	public boolean canFinish(int numCourses, int[][] prerequisites) {

		// 建立图
		HashMap<Integer, List<Integer>> preToFollow = new HashMap<>(); 

		for (int i = 0; i < numCourses; i++)
			preToFollow.put(i, new ArrayList<Integer>());
		
		for (int[] pair : prerequisites) {
			int course = pair[0];
			int preCourse = pair[1];
			preToFollow.get(preCourse).add(course);
		}

		// 建立入度 preCourse -> course(course indegree++)
		int[] indegreeOfCourse = new int[numCourses];

		for (Integer preCourse : preToFollow.keySet())
			for (Integer course : preToFollow.get(preCourse))
				indegreeOfCourse[course]++;

		// 将所有入度为 0 的点，也就是那些没有任何依赖的点，放到宽度优先搜索的队列中
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < numCourses; i++) {
			if (indegreeOfCourse[i] != 0) continue;
			queue.offer(i);
			count++;
		}

		// BFS TOPO
		while (!queue.isEmpty()) {
			int precourse = queue.poll();
			for (Integer course : preToFollow.get(precourse)) {
				indegreeOfCourse[course]--;
				if (indegreeOfCourse[course] != 0) continue;
				queue.offer(course);
				count++;
			}
		}

		return count == numCourses;
	}
}
