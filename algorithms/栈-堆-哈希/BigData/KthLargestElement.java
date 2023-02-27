import java.util.PriorityQueue;

// 找到数组中第K大的元素，N远大于K
// 请注意你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素

public class KthLargestElement {
  public int kthLargestElement2(int[] nums, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int num : nums) {
			// 感受：heap可暴力处理，先加了再说，再判断size（反正顺序是被保证的）
			heap.offer(num);
			if (heap.size() > k) heap.poll();
		}
		return heap.peek();
	}
}
