/**
 * 定一个包含红，白，蓝且长度为 n 的数组，
 * 将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。
 * 我们可以使用整数 0，1 和 2 分别代表红，白，蓝。
 */
public class SortColors {

  public void sortColors(int[] nums) {
		if (nums == null || nums.length <= 1) return;

		int left = 0;
		int right = nums.length - 1;
		int middle = 0;

		while (middle <= right) {
			// 以 middle pointer作为比较对象
			if (nums[middle] == 0) {
				swap(nums, left, middle);
				left++;
				middle++;
			} else if (nums[middle] == 1) {
				middle++;
			} else if (nums[middle] == 2) {
				swap(nums, middle, right);
				right--;
			}
		}
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
