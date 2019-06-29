/**
 * Given an array of integers, find how many pairs in the array such that 
 * their sum is bigger than a specific target number. 
 * Please return the number of pairs.
 * 
 * Input: [2, 7, 11, 15], target = 24
 * Output: 1
 * Explanation: 11 + 15 is the only pair.
 */

public class TwoSumGreaterThanTarget {
  public int twoSum2(int[] nums, int target) {
		if (nums == null || nums.length < 2) return 0;
		Arrays.sort(nums);

		int pairs = 0;
		int left = 0, right = nums.length - 1;

		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum <= target) {
				left++;
			} else {
				pairs = pairs + right - left;
				right--;
			}
		}
		return pairs;
	}
}
