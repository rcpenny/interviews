// 给定一个由n个整数组成的数组，找到给定长度k的连续子数组，
// 该子数组具有最大平均值。你需要输出最大平均值
// 输入: nums = [1,12,-5,-6,50,3] and k = 4
// 输出: 12.75

public class MaxAvgSubarray {
	// 双指针滑动窗口其实更好，但是这里只是练一下prefix-sum
  public double findMaxAverage(int[] nums, int k) {
		int array_len = nums.length;

		// 生成prefix_sum
		int[] prefix_sum = new int[array_len + 1];
		for (int i = 1; i <= array_len; i++)
			prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1];

		int result = Integer.MIN_VALUE;
		for (int i = k; i <= array_len; i++)
			result = Math.max(result, prefix_sum[i] - prefix_sum[i - k]);
		
		return result * 1.0 / k;
	}
}
