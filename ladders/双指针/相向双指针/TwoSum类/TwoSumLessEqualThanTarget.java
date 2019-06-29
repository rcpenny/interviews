/**
 * Given an array of integers,
 * find how many pairs in the array such that their sum is less than or equal to a specific target number. 
 * Please return the number of pairs.
 */
public class TwoSumLessEqualThanTarget {

  public int twoSum5(int[] nums, int target) {
		if (nums == null || nums.length < 2) return 0;
		Arrays.sort(nums);

		int pairs = 0;
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum > target) {
				right--;
			} else {
				pairs = pairs + right - left;
				left++;
			}
		}
		return pairs;
	}
}
