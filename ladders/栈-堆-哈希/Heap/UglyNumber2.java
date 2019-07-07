// 设计一个算法，找出只含素因子2，3，5 的第 n 小的数。
// 符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

public class UglyNumber2 {
	public int nthUglyNumber(int n) {
    PriorityQueue<Long> minheap = new PriorityQueue<Long>();
    Set<Long> visited = new HashSet<>();
    Long[] primes = new Long[] {Long.valueOf(2), Long.valueOf(3), Long.valueOf(5)};
    
    for (Long prime : primes) {
      minheap.offer(prime);
      visited.add(prime);
    }
    
    Long number = Long.valueOf(1);
    for (int i = 1; i < n; i++) {
      number = miheap.poll();  
      for (Long prime : primes) {
        if (visited.contains(prime * number)) continue;
				minheap.offer(prime * number);
				visited.add(prime * number);
      }
    }
    
    return number.intValue();
  }
}
