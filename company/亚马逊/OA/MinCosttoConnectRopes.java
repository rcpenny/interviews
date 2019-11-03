import java.util.PriorityQueue;

class MinCosttoConnectRopes {
  public int connectSticks(int[] sticks) {
    PriorityQueue<Integer> minheap = new PriorityQueue<>();
    
    for (int i = 0; i < sticks.length; i++) {
      minheap.offer(sticks[i]);
    }

    int cost = 0;

    while (minheap.size() > 1) {
      int sum = minheap.poll() + minheap.poll();
      cost += sum;
      minheap.offer(sum);
    }

    return cost;
  }
}