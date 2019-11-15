// To take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

class CourseScheduleAmazon {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] indegrees = new int[numCourses];
    int courseFinished = 0;
    
    Map<Integer, List<Integer>> preToFollow = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      preToFollow.put(i, new ArrayList<>());
    }
    
    // 1. build indegrees and graph preCourse -> followCourses
    for (int[] pair : prerequisites) {
      int preCourse = pair[1];
      int followCourse = pair[0];
      
      indegrees[followCourse]++;
      preToFollow.get(preCourse).add(followCourse);
    }
    
    // 2. add all zero indegree courses
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < indegrees.length; i++) {
      if (indegrees[i] == 0) {
        queue.offer(i);
        courseFinished++;
      }
    }
    
    // 3. pop prepcourses and offer new zero indegree courses
    while (!queue.isEmpty()) {
      int course = queue.poll();
      for (int follow : preToFollow.get(course)) {
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