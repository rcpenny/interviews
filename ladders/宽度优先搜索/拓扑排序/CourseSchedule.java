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
    int[] indegrees = new int[numCourses];
    Map<Integer, List<Integer>> preToFollow = new HashMap<>();

		// 建立图与入度
    for (int i = 0; i < numCourses; i++) {
      preToFollow.put(i, new ArrayList<>());
    }

    for (int[] pair : prerequisites) {
      int preCourse = pair[1];
      int followCourse = pair[0];

      indegrees[followCourse]++;
      preToFollow.get(preCourse).add(followCourse);
    }

    int courseFinished = 0;

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegrees[i] == 0) {
        queue.offer(i);
        courseFinished++;
      }
    }

    while (!queue.isEmpty()) {
      int preCourse = queue.poll();
      List<Integer> followCourses = preToFollow.get(preCourse);

      for (int follow : followCourses) {
        indegrees[follow]--;
        if (indegrees[follow] == 0) {
          queue.offer(follow);
          courseFinished++;
        }
      }
    }

    return courseFinished == numCourses;
  }
}
