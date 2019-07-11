/**
 * https://www.lintcode.com/problem/find-the-duplicate-number/
 * 
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

 // 思路：大于4的数字应该是2个，但是只有1个.所以4是有问题的.
public class FindDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int l = 1;
		int r = nums.length - 1;  // n
		
		// O(logN)
		while (l + 1 < r) {
			int mid = l + (r - l) / 2;
			if (count(nums, mid) <= mid) l = mid;
			else r = mid;
		}
		
		if (count(nums, l) <= l) return r;
		return l;
	}

	// O(n)
	private int count(int[] nums, int mid) {
		int cnt = 0;
		for (int item : nums) if (item <= mid) nt++;
		return cnt;
	}
}
