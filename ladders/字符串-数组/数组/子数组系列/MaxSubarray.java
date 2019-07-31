// 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和
// 输入：[−2,2,−3,4,−1,2,1,−5,3]
// 输出：6
// 解释：符合要求的子数组为[4,−1,2,1]，其最大和为 6
// lint41

public class MaxSubarray {
  public int maxSubArray(int[] nums) {
		int prefix_sum = 0;
		int min_prefix_sum = 0;
		int max_sum = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {
			// 更新当前前缀和
			prefix_sum = prefix_sum + nums[i]; 
			
			// 先算此点前缀和，于之前min_sum的差. 更新max_sum
			max_sum = Math.max(max_sum, prefix_sum - min_prefix_sum);

			// 更新min_prefix_sum  sofar
			min_prefix_sum = Math.min(min_prefix_sum, prefix_sum);
		}

		return max_sum;
	}
}
