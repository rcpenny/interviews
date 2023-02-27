/**
 * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：

所有小于k的元素移到左边
所有大于等于k的元素移到右边
返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] >= k。

Example
Challenge
使用 O(n) 的时间复杂度在数组上进行划分。

Notice
你应该真正的划分数组 nums，而不仅仅只是计算比 k 小的整数数，如果数组 nums 中的所有元素都比 k 小，则返回 nums.length。
 */
public class PartitionArray {

  public int partitionArray(int[] nums, int k) {
		if (nums == null || nums.length == 0) return 0;

		int left = 0, right = nums.length - 1;
		while (left <= right) {
			while (left <= right && nums[left] < k) left++;
			while (left <= right && nums[right] >= k) right--;

			if (left <= right) {
				int tmp = nums[left];
				nums[left] = nums[right];
				nums[right] = tmp;
				left++;
				right--;
			}
		}
		return left;
	}

}
