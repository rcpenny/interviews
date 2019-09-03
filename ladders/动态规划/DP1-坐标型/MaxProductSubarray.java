// 找出一个序列中乘积最大的连续子序列（至少包含一个数）
// 输入:[2,3,-2,4] 输出:6

// 确定状态: p[i] 在i位置的最大正积，n[i] 在i位置的最大负积
// 转移方程: 根据正负情况判断，max, min一下
// 初态边界: p[0] = nums[0] or n[0] = 0
// 计算顺序: 0 -> len

public class MaxProductSubarray {
	public int maxProduct(int[] nums) {
		if (nums.length == 1) return nums[0];
		
		int len = nums.length;
		int[] p = new int[len];  // 其实合并成f[len][2]就好了
		int[] n = new int[len];  // 不过pos neg 还挺清楚

		// 初始化
		if (nums[0] >= 0) 
			p[0] = nums[0];
		else 
			n[0] = nums[0];
		
		int max = Math.max(p[0], n[0]);

		for (int i = 1; i < len; i++) {
			int cur = nums[i];

			if (cur == 0) {
				p[i] = 0;
				n[i] = 0;
				continue;
			}

			if (cur > 0) {
				p[i] = Math.max(p[i - 1] * cur, cur); // 特殊情况：如果p[i-1]=0, cur等于7
				n[i] = Math.min(n[i - 1] * cur, 0);
			} else if (cur < 0) {
				p[i] = Math.max(n[i - 1] * cur, 0);
				n[i] = Math.min(p[i - 1] * cur, cur); // 特殊情况：如果p[i-1]=0, cur等于-7
			}

			max = Math.max(max, p[i]);
		}

		return max;
	}
}
