// 给一 只含有正整数 的 非空 数组, 找到这个数组是否可以划分为 两个 元素和相等的子集

// 用dp解，背包问题
// 能否背到总价值的一半
// 01背包即可

public class PartitionEqualSubsetSum {

	// dfs解法
  private boolean result = false;
  public boolean canPartition_(int[] nums) {
    if (nums == null || nums.length < 2) return result;
    int sum = 0;
    for (int num : nums) sum += num;
    if (sum % 2 != 0) return result;
    
    find(nums, 0, sum, 0);

    return result;
  }

  private void find(int[] nums, int index, int sum, int cur_sum) {
    if (result) return;
    if (cur_sum * 2 == sum) {
      result = true;
      return;
    }

    for (int i = index; i < nums.length; i++)
      find(nums, index + 1, sum, cur_sum + nums[i]);
	}
	
	// 动态规划解法
	// 1. 确定状态 f[i][j] 前i个数字是否可以拼出sum j,(j <= sum / 2)
	// 2. 转移方程 f[i][j] initiliazed to false
	// 					  = true when f[i-1][j] is true
	//         or = true when f[i-1][j - nums[i - 1]] = true
	// 3. 初态边界  
	// 4. 顺序计算

	// 就是backpack 1
	public boolean canPartition(int[] nums) {
		if (nums == null || nums.length < 2) return false;

    int sum = 0;
		for (int num : nums) sum += num;
		if (sum % 2 != 0) return false;

		int n = nums.length;
		int target = sum / 2;

		boolean[][] f = new boolean[n + 1][target + 1];

		f[0][0] = true; // 前0个数字可以拼出和为0
		for (int j = 1; j <= target; j++) f[0][j] = false; // 前0个数字不能拼出和为j(j > 0)

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= target; j++) {
				f[i][j] = false;

				if (f[i - 1][j] == true) {
					f[i][j] = true;
					continue;
				}

				if (j - nums[i - 1] >= 0 && f[i - 1][j - nums[i - 1]] == true) {
					f[i][j] = true;
					continue;
				}
			}
		}

		for (int i = 1; i <= n; i++)
			if (f[i][target] == true) return true;
		
		return false;
	}
}
