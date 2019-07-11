import java.util.List;

// 给定一个整数数组，找出两个 不重叠 子数组使得它们的和最大。
// 每个子数组的数字在数组中的位置应该是连续的。
// 返回最大的和。

// 输入:[1, 3, -1, 2, -1, 2]
// 输出:7
// 解释:
// 最大的子数组为 [1, 3] 和 [2, -1, 2] 或者 [1, 3, -1, 2] 和 [2].

public class MaxSubarray2 {
  public int maxTwoSubArrays(List<Integer> nums) {
		int[] array = new int[nums.size()];
		for (int i = 0; i < nums.size(); i++) array[i] = nums.get(i);

		// 枚举两边max, 从左向右生成一次，从右向左生成一次
		int[] maxFromLeft = new int[array.length];
		int[] maxFromRight = new int[array.length];

		int max_sum = Integer.MIN_VALUE, prefix_sum = 0, min_sofar = 0;
		for (int i = 0; i < array.length; i++) {
			prefix_sum = prefix_sum + array[i];
			max_sum = Math.max(max_sum, prefix_sum - min_sofar);
			maxFromLeft[i] = max_sum;
			min_sofar = Math.min(min_sofar, prefix_sum);
		}

		max_sum = Integer.MIN_VALUE;
		prefix_sum = 0;
		min_sofar = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			prefix_sum = prefix_sum + array[i];
			max_sum = Math.max(max_sum, prefix_sum - min_sofar);
			maxFromRight[i] = max_sum;
			min_sofar = Math.min(min_sofar, prefix_sum);
		}

		int max_sum_two = Integer.MIN_VALUE;
		for (int i = 0; i < array.length - 1; i++) {
			int left = maxFromLeft[i];
			System.out.println(left);
			int right = maxFromRight[i + 1];
			System.out.println(right);
			max_sum_two = Math.max(max_sum_two, left + right);
		}

		return max_sum_two;
	}
}
