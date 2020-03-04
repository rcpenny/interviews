// 给定一个非负整数的列表a1,a2,...an，再给定一个目标S。
// 现在用+和-两种运算，对于每一个整数，选择一个作为它前面的符号。
// 找出有多少种方法，使得这些整数的和正好等于S

// 输入: nums为 [1, 1, 1, 1, 1], S 为 3. 
// 输出: 5
// 解释: 

// -1+1+1+1+1 = 3
// +1-1+1+1+1 = 3
// +1+1-1+1+1 = 3
// +1+1+1-1+1 = 3
// +1+1+1+1-1 = 3

// 有5种方法让和为3.

// 搜索的时间复杂度：O(答案总数 * 构造每个答案的时间)  O(2^n * 1)
// DP解法：https://www.jiuzhang.com/solution/target-sum/#tag-highlight

public class TargetSum {
	private int ways = 0;
	
	public int findTargetSumWays(int[] nums, int target) {
		if (nums == null || nums.length == 0) return ways;
	
		find(nums, 0, target, 0);
	
		return ways;
	}
	
	private void find(int[] nums, int index, int target, int sum) {
		if (sum ==  target && index == nums.length) ways++;
		if (index == nums.length) return;
	
		find(nums, index + 1, target, sum + nums[index]);
		find(nums, index + 1, target, sum - nums[index]);
	}	
}
