// 给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度
// 输入: [4,2,4,5,3,7]  输出:  4   LIS 是 [2,4,5,7]

// 1. 确定状态 最后一步 子问题   f[i] 以 a[j] 为结尾的最长上升子序列
// 2. 设计状态转移方程     f[i] = max {1, f[i] + 1 (i < j && a[i] < a[j])}
// 3. 确定初始态/边界： 无
// 4. 按照一定顺序计算： 0 —> n

// O（N*N)
public class LongestIncreasingSubsequence {

  public int longestIncreasingSubsequence(int[] nums) {
		int n = nums.length;
		int[] f = new int[n];

		int max = 0;
		for (int i = 0; i < n; i++) {
			f[i] = 1;
			for(int j = 0; j < i; j++) {
				if (nums[j] < nums[i])
					f[i] = Math.max(f[j], f[i] + 1);
			}
			max = Math.max(max, f[i]);
		}

		return max;
	}
}
