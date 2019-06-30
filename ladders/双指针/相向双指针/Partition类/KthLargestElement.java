/**
 * Find K-th largest element in an array.
 */
public class KthLargestElement {
  public int kthLargestElement(int k, int[] nums) {
		return quickSelect(nums, 0, nums.length - 1, nums.length - k);
	}

	// partition array
	private int quickSelect(int[] nums, int start, int end, int k) {
		int left = start, right = end;
		int pivot = nums[(left + right) / 2];

		while (left <= right) {
			while (left <= right && nums[left] < pivot) left++;
			while (left <= right && nums[right] > pivot) right--;
			if (left <= right) {
        swap(nums, left, right);
				left++;
				right--;
			}
		}

		// start-------right-left--------end
		if (k <= right) {
			return quickSelect(nums, start, right, k);
		}
		if (k >= left) {
			return quickSelect(nums, left, end, k);
		}
		return nums[k];
	}
	
	private void swap(int[] nums, int a, int b) {
	  int tmp = nums[a];
	  nums[a] = nums[b];
	  nums[b] = tmp;
	}
}
