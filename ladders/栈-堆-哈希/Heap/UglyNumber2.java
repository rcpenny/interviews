// 设计一个算法，找出只含素因子2，3，5 的第 n 小的数。

// 符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

// Example
// 样例 1：

// 输入：9
// 输出：10
// 样例 2：

// 输入：1
// 输出：1
// Challenge
// 要求时间复杂度为 O(nlogn) 或者 O(n)

public class UglyNumber2 {
	public int nthUglyNumber(int n) {
    PriorityQueue<Long> pq = new PriorityQueue<Long>();
    Set<Long> set = new HashSet<Long>();
    
    Long[] primes = new Long[3];
    primes[0] = Long.valueOf(2);
    primes[1] = Long.valueOf(3);
    primes[2] = Long.valueOf(5);
    
    for (int i = 0; i < 3; i++) {
      pq.offer(primes[i]);
      set.add(primes[i]);
    }
    
    Long number = Long.valueOf(1);
    for (int i = 1; i < n; i++) {
      number = pq.poll();
      
      for (int j = 0; j < 3; j++) {
        if (!set.contains(primes[j] * number)) {
          pq.offer(primes[j] * number);
          set.add(primes[j] * number);
        }
      }
    }
    
    return number.intValue();
  }
}
