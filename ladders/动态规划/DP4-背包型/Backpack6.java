// 给出一个都是正整数的数组 nums，其中没有重复的数
// 从中找出所有的和为 target 的组合个数。
// 一个数可以在组合中出现多次。
// 数的顺序不同则会被认为是不同的组合。

// 输入: nums = [1, 2, 4] 和 target = 4 输出: 6
// 可能的所有组合有：[1, 1, 1, 1] [1, 1, 2]
// [1, 2, 1] [2, 1, 1] [2, 2] [4]

// 1. 确定状态:f[w]有多少种组合可以拼出重量w
// 2. 转移方程: f[w] = f[w - A[0]] + f[w - A[1]] + ... f[w - A[n - 1]]
// 3. 初态边界: f[0] = 0
// 4. 顺序计算: f[0] -> f[target]

// 因为不用返回所有方案，所以不需要递归. combination sum 2

public class Backpack6 {
  public int backPackVI(int[] nums, int target) {
		int n = nums.length;
		if (n == 0) return 0;

		int[] f = new int[target + 1];

		// initialization, 那个数的值至少有一种可能性
		f[0] = 0;
		for (int i = 0; i < nums.length; i++)
			f[nums[i]] = 1;

		for (int w = 1; w <= target; w++)
			for (int j = 0; j < nums.length; j++)
				if (w - nums[j] > 0) 
					f[w] += f[w - nums[j]];
		
		return f[target];
	}
}
