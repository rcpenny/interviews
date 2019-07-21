// 给定一个长度为N的01串
// 要求翻转其中最少的01，使得结果中没有“01”这样的子串

// • 输入：[1, 0, 1, 0, 1, 0]
// • 输出：2 （变成[1, 1, 1, 1, 1, 0]）

// 确定状态: f[i][0]表示A[i-1]变成0的情况下，前i位最少反转次数  f[i][1]同理
// 转移方程: f[i][j] = min{f[i-1][k] + 1} (k,j)!=(0,1) A[i-1]!=j
// 初态边界: f[0][0] = 0, f[0][1] = 0, 
// 计算顺序: 左向右

public class DigitalFlip {
	public int flipDigit(int[] nums) {
		int n = nums.length;
		if (n <= 1) return 0;

		int[][] f = new int[n + 1][2];
		f[0][0] = f[0][1] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 2; j++) {
				f[i][j] = Integer.MAX_VALUE;

				// A[i - 1] --> j，should I flip?
				int t = 0;
				if (nums[i - 1] != j) t = 1;

				// 枚举前一位 A[i - 2] --> k
				for(int k = 0; k < 2; k++) {
					if (k == 0 && j == 1) continue;
					f[i][j] = Math.min(f[i][j], f[i - 1][k] + t);
				}
			}
		}

		return Math.min(f[n][0], f[n][1]);
	}
}
