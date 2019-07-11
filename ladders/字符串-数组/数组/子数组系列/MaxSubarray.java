// 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和
// 输入：[−2,2,−3,4,−1,2,1,−5,3]
// 输出：6
// 解释：符合要求的子数组为[4,−1,2,1]，其最大和为 6

public class MaxSubarray {
  public int maxSubArray(int[] nums) {
		int prefix_sum = 0;
		int min_sum_sofar = 0; // 
		int max_sum = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {
			prefix_sum = prefix_sum + nums[i];

			// 先算此点前缀和，于之前min_sum的差.
			max_sum = Math.max(max_sum, prefix_sum - min_sum_sofar);
			// 更新min_sum
			min_sum_sofar = Math.min(min_sum_sofar, prefix_sum);
		}

		return max_sum;
	}
}
