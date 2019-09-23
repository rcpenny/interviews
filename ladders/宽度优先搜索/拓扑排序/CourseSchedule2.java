import java.uitl.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求任意1个拓扑序（Topological Order）
 * 
 * 你需要去上n课，这些课被标号为 0 到 n-1 。
 * 有一些课程需要“前置课程”，比如如果你要上课程0，你需要先学课程1
 * 我们用一个匹配来表示他们： [0,1]
 * 给你课程的总数量和一些前置课程的需求，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 */

public class CourseSchedule2 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		HashMap<Integer, List<Integer>> graph = new HashMap<>(); 
		int[] indegreeOfCourse = new int[numCourses];

		// 建立图 preCourse -> {following course numbers}
		// 忘了这一步造成null pointer exception
		for (int i = 0; i < numCourses; i++)
			graph.put(i, new ArrayList<Integer>());
		
		for (int[] pair : prerequisites) {
			int course = pair[0];
			int preCourse = pair[1];
			graph.get(preCourse).add(course);
		}

		// 建立入度
		for (Integer preCourse : graph.keySet())
			for (Integer course : graph.get(preCourse))
				indegreeOfCourse[course]++;

		// 将所有入度为 0 的点，也就是那些没有任何依赖的点，放到宽度优先搜索的队列中
		int count = 0;
		int[] result = new int[numCourses];
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegreeOfCourse[i] != 0) continue;
			queue.offer(i);
			result[count] = i;
			count++;
		}

		while (!queue.isEmpty()) {
			int preCourse = queue.poll();
			for (Integer course : graph.get(preCourse)) {
				indegreeOfCourse[course]--;
				if (indegreeOfCourse[course] != 0) continue;
				queue.offer(course);
				result[count] = course;
				count++;
			}
		}

		return numCourses == count ? result : new int[0];
	}
}
