// Given an array with positive and negative numbers, 
// find the maximum average subarray which length should be greater or equal to given length k.

// Input:  [1,12,-5,-6,50,3] 3
// Output: 15.667

// 如果最大平均值是T
// 那么 (A[left] + ... + A[right]) / (right - left + 1) >= T
// 转换成 (A[left] - T) + ... + (A[right] - T) >= 0     right - left + 1 >= k
 
//此题前缀和暴力可解，更好的是用二分答案
public class MaxAvgSubarray2 {
	public double maxAverage(int[] nums, int k) {
		// get max avg range (min -> max)
		double start = nums[0], end = nums[0];
		for (int i = 0; i < nums.length; i++) {
			start = Math.min(start, nums[i]);
			end = Math.max(end, nums[i]);
		}

		// 二分 O(logN)
		while (start + 1e-5 < end) {
			double mid = start + (end - start) / 2;
			if (canFind(nums, k, mid)) start = mid;
			else end = mid;
		}

		return start;
	}

	private boolean canFind(int[] nums, int k, double avg) {
		double right_sum = 0, left_sum = 0, min_left_sum = 0;
		for (int i = 0; i < k; i++) right_sum += nums[i] - avg;

		// 等于nums.length是为了让最后一步算的right_sum再比一次
		for (int i = k; k <= nums.length; i++) {
			if (right_sum - min_left_sum >= 0) return true;
			if (i < nums.length) {
				right_sum += nums[i] - avg;
				left_sum += nums[i - k] - avg;
				min_left_sum = Math.min(min_left_sum, left_sum);
			}
		}

		return false;
	}
}
