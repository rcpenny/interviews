/** quick sort */

public class QuickSort {
  public void sortIntegers2(int[] nums) {
		if (nums == null) return;
		quicksort(nums, 0, nums.length - 1);
	}

	private void quicksort(int[] nums, int start, int end) {
		if (start > end) return;
		int left = start;
		int right = end;
		int pivot = nums[(end - start) / 2 + start];

		while (left <= right) {
			while (left <= right && nums[left] < pivot) left++;
			while (left <= right && nums[right] > pivot) right--;
			if (left <= right) {
				swap(nums, left, right);
				left++;
				right--;
			}
		}
		// start ----right-left ----- end
		quicksort(nums, start, right);
		quicksort(nums, left, end);
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
