import java.util.Arrays;
import java.util.PriorityQueue;

// Given an integer array, find the top k largest numbers in it.
// Input: [3, 10, 1000, -99, 4, 100] and k = 3
// Output: [1000, 100, 10]

public class TopKLargestNumber {
  public int[] topk(int[] nums, int k) {
		int [] result = new int[k];
		// 用maxheap代码少，但是需O(nlogn),minheap可以排除掉小于peek的情况
		PriorityQueue<Integer> minheap = new PriorityQueue<>(k);

		for (int number : nums) {
			if (minheap.size() < k) {
				minheap.offer(number);
				continue;
			}
			if (number > minheap.peek()) {
				minheap.poll();
				minheap.offer(number);
			}
		}
		
		for (int i = k - 1; i >= 0; i--)
			result[i] = minheap.poll();
		
		return result;
	}
}
