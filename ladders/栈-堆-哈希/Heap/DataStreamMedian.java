import java.util.Collections;
import java.util.PriorityQueue;

// 数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数。

// 输入: [4,5,1,3,2,6,0]
// 输出: [4,4,4,3,3,3,3]
// 样例说明：
// [4], [4,5] 和 [4,5,1] 的中位数是 4.
// [4,5,1,3], [4,5,1,3,2], [4,5,1,3,2,6] 和 [4,5,1,3,2,6,0] 的中位数是 3.

// 中位数是排序后数组的中间值，如果有数组中有n个数，则中位数为A[(n-1)/2]A[(n−1)/2]
// Challenge
// 时间复杂度为O(nlogn)

public class DataStreamMedian {
  public int[] medianII(int[] nums) {
    if (nums == null) return new int[] {};

    int[] results = new int[nums.length];

    // max heap is smaller part, min heap is bigger part
    PriorityQueue<Integer> maxheap = 
      new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minheap = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      addNumber(maxheap, minheap, nums[i]);
      results[i] = maxheap.peek();
    }
    return results;
  }

  // 不论num大小，都去maxheap minheap里面过一遍 自然就顺序对了
  // ：）真暴力 但这样少了很多if else 判断了 就很好
  // 然后 将中位数一直维持在更小的那个堆,小数堆maxheap的size >= 大数堆minheap
  // median就一直在小数堆头了 ：）
  private void addNumber(PriorityQueue<Integer> maxheap, 
    PriorityQueue<Integer> minheap, int num) {
    maxheap.offer(num);
    minheap.offer(maxheap.poll());
    if (minheap.size() > maxheap.size())
      maxheap.offer(minheap.poll());
  }
}
