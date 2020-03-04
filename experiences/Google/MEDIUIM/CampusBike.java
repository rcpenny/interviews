// leet1057
// 自己面了，说多了都是泪

class Pair {
  int worker; // index in workers
  int bike;   // index in bikes
  int distance;
  Pair(int worker, int bike, int distance) {
    this.worker = worker;
    this.bike = bike;
    this.distance = distance;
  }
}

class CampusBike {
  public int[] assignBikes(int[][] workers, int[][] bikes) {
    Set<Integer> workers_index = new HashSet<>();
    Set<Integer> bikes_index = new HashSet<>();
    
    int i, j;
    
    for (i = 0; i < workers.length; i++) workers_index.add(i);
    for (j = 0; j < bikes.length; j++) bikes_index.add(j);

    PriorityQueue<Pair> minheap = new PriorityQueue<>((p1, p2) -> {
      if (p1.distance != p2.distance) return p1.distance - p2.distance;
      if (p1.worker != p2.worker) return p1.worker - p2.worker;
      return p1.bike - p2.bike;
    });
    
    for (i = 0; i < workers.length; i++) {
      for (j = 0; j < bikes.length; j++) {
        int dis = getDistance(workers[i], bikes[j]);
        Pair p = new Pair(i, j, dis);
        minheap.offer(p);
      }
    }
    
    int[] result = new int[workers.length];
    
    while (!workers_index.isEmpty()) {
      Pair p = minheap.poll();
      if (workers_index.contains(p.worker) && bikes_index.contains(p.bike)) {
        workers_index.remove(p.worker);
        bikes_index.remove(p.bike);
        
        result[p.worker] = p.bike;
      }
    }
    
    return result;
  }
  
  private int getDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }
}
