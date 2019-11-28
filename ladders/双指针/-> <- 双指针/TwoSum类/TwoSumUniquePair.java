/**
 * Given an array of integers, 
 * find how many unique pairs in the array such that 
 * their sum is equal to a specific target number. Please return the number of pairs.
 * 
 * Input: nums = [1,1,2,45,46,46], target = 47 
 * Output: 2
 * Explanation:
 * 1 + 46 = 47
 * 2 + 45 = 47
 */
public class TwoSumUniquePair {
  public int twoSum6(int[] nums, int target) {
		if (nums == null || nums.length < 2) return 0;
		
		Arrays.sort(nums);
		int left = 0, right = nums.length - 1;
		int pairs = 0;

		// 双指针的while loop，if continue去重.
		while (left < right) {
			if (left != 0 && nums[left] == nums[left - 1]) {
				left++;
				continue;
			}
			if (right != nums.length - 1 && nums[right] == nums[right + 1]) {
				right--;
				continue;
			}

			int sum = nums[left] + nums[right];
			if (sum == target) {
				pairs++;
				left++;
				right--;
			} else if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			}
		}
		return pairs;
	}
}
