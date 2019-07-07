import java.util.Collections;
import java.util.PriorityQueue;

// 数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数
// 输入: [4,5,1,3,2,6,0]
// 输出: [4,4,4,3,3,3,3]
// Challenge
// 时间复杂度为O(nlogn)

public class DataStreamMedian {
  public int[] medianII(int[] nums) {
    int[] results = new int[nums.length];

    // maxheap存小数堆，minheap存大数堆
    PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minheap = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      addNumber(maxheap, minheap, nums[i]);
      results[i] = maxheap.peek();
    }
    return results;
  }

	// 本来有很多if要考虑，比如进来的数字与两个heap peek的大小比较，但是这个add方法
  // 不论nums大小，都去maxheap minheap里面过一遍 自然就顺序对了 真暴力 堆真神奇！
  // 最后 维持小数堆maxheap.size >= minheap.size，median自然在小数堆头了 ：）
  private void addNumber(PriorityQueue<Integer> maxheap, PriorityQueue<Integer> minheap, int num) {
    maxheap.offer(num);
		minheap.offer(maxheap.poll());
    while (minheap.size() > maxheap.size()) maxheap.offer(minheap.poll());
  }
}
