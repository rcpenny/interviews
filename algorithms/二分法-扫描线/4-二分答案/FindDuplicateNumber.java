/** 
 * 给出一个数组 nums 包含 n + 1 个整数，每个整数是从 1 到 n (包括边界)，保证至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 * 
 * 1.不能修改数组(假设数组只能读)
 * 2.只能用额外的O(1)的空间
 * 3.时间复杂度小于O(n^2)
 * 4.数组中只有一个重复的数，但可能重复超过一次
 * 输入: [5,4,4,3,2,1]
 * 输出:  4
 */

 // 思路：小于5的数字应该是4个，但是有5个.所以1-4中有dupes.
 // 在答案范围 1 -> n 之间二分
 
public class FindDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int smaller = 1;
		int larger = nums.length - 1;  // n
		
		// O(logN)
		while (smaller + 1 < larger) {
			int mid = smaller + (larger - smaller) / 2;
			if (count(nums, mid) <= mid) smaller = mid;
			else larger = mid;
		}
		
		if (count(nums, smaller) <= smaller) return larger;
		return smaller;
	}

	// O(n)
	// 找数列中不大于mid的个数
	private int count(int[] nums, int mid) {
		int cnt = 0;
		for (int item : nums) 
			if (item <= mid) cnt++;

		return cnt;
	}
}
