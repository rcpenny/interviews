/**
 * 给定整数数组num，从中找到两个数字使得他们和最接近target，
 * 返回两数和与 target 的差的 绝对值
 * 
 * 输入: nums = [-1, 2, 1, -4] 并且 target = 4
 * 输出: 1
 * 解释:
 * 最小的差距是1，(4 - (2 + 1) = 1).
 */

public class TwoSumClosetToTarget {
	public int twoSumClosest(int[] nums, int target) {
		int minDiff = Integer.MAX_VALUE;
		if (nums == null || nums.length < 2) return minDiff;

		Arrays.sort(nums);

		int left = 0, right = nums.length - 1;
		int sum = 0;
		
		while (left < right) {
			sum = nums[left] + nums[right];
			if (sum ==  target) {
				return 0;
			} else if (sum < target) {
				minDiff = Math.min(target - sum, minDiff);
				left++;
			} else if (sum > target) {
				minDiff = Math.min(sum - target, minDiff);
				right--;
			}
		}
		return minDiff;
	}
}
