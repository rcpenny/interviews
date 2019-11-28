// Given a list of integers, which denote a permutation.
// Find the next permutation in ascending order

// 从最后一个位置开始，找到一个上升点，上升点之前的无需改动。
// 然后，翻转上升点之后的降序。
// 在降序里，找到第一个比上升点大的，交换位置

public class NextPermutation {

	public int[] nextPermutation(int[] nums) {
		int n = nums.length;
		if (n <= 1) return nums;
		
		// 1,4,3,2 -> 2,1,3,4 找到index 4，从后往前的地一个peak，下降点
    int index = n - 1;
    while (index > 0 && nums[index] <= nums[index - 1]) 
      index--;
		
		// reverse下降峰 4,3,2
		reverse(nums, index, n - 1);
	
		// 特殊情况 4,3,2,1 -> 1,2,3,4
		if (index == 0) return nums;

		// 1,2,3,4 -> 2,1,3,4将peak左边与右边地一个比他大的数交换
    for (int j = index; j < n; j++) {
      if (nums[j] > nums[index - 1]) {
        swap(nums, index - 1, j);
        break;
      }
    }

		return nums;
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i, j);
			i++; 
			j--;
		}
	}
}
