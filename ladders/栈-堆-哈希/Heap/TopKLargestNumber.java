import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

// Given an integer array, find the top k largest numbers in it.

// Example
// Example1

// Input: [3, 10, 1000, -99, 4, 100] and k = 3
// Output: [1000, 100, 10]

public class TopKLargestNumber {
  public int[] topk(int[] nums, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>(k, Collections.reverseOrder());
		for (int number : nums) heap.offer(number);
		int [] result = new int[k];
		for (int i = 0; i < k; i++) result[i] = heap.poll();
		return result;
	}
}
