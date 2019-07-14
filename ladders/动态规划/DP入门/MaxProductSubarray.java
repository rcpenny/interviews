// 找出一个序列中乘积最大的连续子序列（至少包含一个数）
// 输入:[2,3,-2,4] 输出:6

// 确定状态: p[i] 在i位置的最大正积，n[i] 在i位置的最大负积
// 转移方程: 根据正负情况判断，max, min一下
// 初态边界: p[0] = nums[0] or n[0] = 0
// 计算顺序: 0 -> nums.length

public class MaxProductSubarray {
	public int maxProduct(int[] nums) {
		if (nums.length == 1) return nums[0];
		int max = Integer.MIN_VALUE;
		
		int len = nums.length;
		int[] p = new int[len];
		int[] n = new int[len];

		if (nums[0] >= 0) p[0] = nums[0];
		else n[0] = nums[0];
		max = Math.max(p[0], n[0]);

		for (int i = 1; i < len; i++) {
			int cur = nums[i];
			if (cur == 0) continue;
			if (cur > 0) {
				p[i] = Math.max(p[i - 1] * cur, cur);
				n[i] = Math.min(n[i - 1] * cur, 0);
			} else {
				p[i] = Math.max(n[i - 1] * cur, 0);
				n[i] = Math.min(p[i - 1] * cur, cur);
			}
			max = Math.max(max, p[i]);
		}

		return max;
	}
}
