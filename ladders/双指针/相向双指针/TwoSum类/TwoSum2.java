/**
 * Two Sum II - Input array is sorted
 */
public class TwoSum2 {
  public int[] twoSum(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		while (start < end) {
			int sum = nums[start] + nums[end];
			if (sum < target) {
				start++;
			} else if(sum > target) {
				end--;
			} else {
				break;
			}
		}
		int[] result = {start + 1, end + 1};
		return result;
	}
}
