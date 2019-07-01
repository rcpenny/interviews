/**
 * Partition an unsorted integer array into three parts:

		The front part < low
		The middle part >= low & <= high
		The tail part > high
		Return any of the possible solutions.
 */
public class PartitionArray2 {
  public void partition2(int[] nums, int low, int high) {
		int left = 0, right = nums.length - 1;

		// first partition, all < low to the left
		while (left <= right) {
			while (left <= right && nums[left] < low) left++;
			while (left <= right && nums[right] >= low) right--;
			if (left <= right) {
				swap(nums, left, right);
				left++;
				right--;
			}
		}

		right = nums.length - 1;

		// second partition, all < high to the right
		while (left <= right) {
			while (left <= right && nums[left] <= high) left++;
			while (left <= right && nums[right] > high) right--;
			if (left <= right) {
				swap(nums, left, right);
				left++;
				right--;
			}
		}
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
