/** Partition an integers array into odd number first and even number second. */
// Input: [1,4,2,3,5,6]
// Output: [1,3,5,4,2,6]

public class PartitionArrayByOddEven {
  public void partitionArray(int[] nums) {
		if (nums == null) return;
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			while (left <= right && nums[left] % 2 == 1) left++;
			while (left <= right && nums[right] % 2 == 0) right--;
			if (left <= right) {
				int tmp = nums[left];
				nums[left] = nums[right];
				nums[right] = tmp;
				left++;
				right--;
			}
		}
	}
}
