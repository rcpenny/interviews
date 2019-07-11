import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 
// 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
// 输入:[1,2,7,7,8] 3
// 输出: [7,7,8]

public class SlidingWindowMax {
  public List<Integer> maxSlidingWindow(int[] nums, int k) {
		Deque<Integer> deque = new LinkedList<>();
		List<Integer> results = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekFirst()]) deque.pollFirst();
			deque.offerFirst(i);

			if (i + 1 >= k) results.add(nums[deque.peekLast()]);

			if (deque.peekLast() == i + 1 - k) deque.pollLast();
		}

		return results;
	}
}
