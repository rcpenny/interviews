import java.util.PriorityQueue;

// 找到数组中第K大的元素，N远大于K。
// 请注意你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
public class KthLargestElement {
  public int kthLargestElement2(int[] nums, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>(k);
		for (int num : nums) {
			heap.offer(num);
			if (heap.size() > k) heap.poll();
		}
		return heap.peek();
	}
}
