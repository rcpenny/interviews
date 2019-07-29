/**
 * Given an array nums,  move all 0's to the end of it while 
 * maintaining the relative order of the non-zero elements.
 */

 // 反过来想，把所有non-zero拿到front去，快慢双指针
public class MoveZeros {
  public void moveZeroes(int[] nums) {
		int fast = 0, slow = 0;

		while (fast < nums.length) {
			if (nums[fast] != 0) {
				swap(nums, fast, slow);
				slow++;
			}
			fast++;
		}
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
