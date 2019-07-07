import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

// 给定一个包含 n 个整数的数组，和一个大小为 k 的滑动窗口,
// 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的中位数
// 参考：data stream median那道题

public class SlidingWindowMedian {
  public List<Integer> medianSlidingWindow(int[] nums, int k) {
		List<Integer> result = new ArrayList<>();

		PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minheap = new PriorityQueue<>();

		for (int i = 0; i < nums.length; i++) {
			if (i >= k) removeNumber(nums[i - k], maxheap, minheap);
			addNumber(nums[i], maxheap, minheap);
			if (i >= k - 1) result.add(maxheap.peek());
		}

		return result;
	}
	
	private void addNumber(int num, PriorityQueue<Integer> maxheap, PriorityQueue<Integer> minheap) {
		maxheap.offer(num);
		minheap.offer(maxheap.poll());
		while (maxheap.size() < minheap.size()) maxheap.offer(minheap.poll());
	}

	private void removeNumber(int num, PriorityQueue<Integer> maxheap, PriorityQueue<Integer> minheap) {
		if (num <= maxheap.peek()) maxheap.remove(num);
		else minheap.remove(num);
		while (maxheap.size() < minheap.size()) maxheap.offer(minheap.poll());
	}
}
