// 给定一个整数数组，找出两个不重叠的子数组A和B，
// 使两个子数组和的差的绝对值|SUM(A) - SUM(B)|最大。
// 返回这个最大的差值
public class MaxSubarrayDiff {
  public int maxDiffSubArrays(int[] nums) {
    int[] maxFromLeft = new int[nums.length];
    int[] maxFromRight = new int[nums.length];
    int[] minFromLeft = new int[nums.length];
    int[] minFromRight = new int[nums.length];

		int max_sum = Integer.MIN_VALUE;
		int min_sum = Integer.MAX_VALUE;
    int min_sofar = 0, max_sofar = 0;
    int prefix_sum = 0;
		
		// 从左扫
		for (int i = 0; i < nums.length; i++) {
      prefix_sum = prefix_sum + nums[i];
      max_sum = Math.max(prefix_sum - min_sofar, max_sum);
      maxFromLeft[i] = max_sum;
      min_sofar = Math.min(min_sofar, prefix_sum);

      min_sum = Math.min(min_sum, prefix_sum - max_sofar);
      minFromLeft[i] = min_sum;
      max_sofar = Math.max(max_sofar, prefix_sum);
    }

    max_sum = Integer.MIN_VALUE;
    min_sum = Integer.MAX_VALUE;
    min_sofar = 0;
    max_sofar = 0;
    prefix_sum = 0;
		
		// 从右扫
		for (int i = nums.length - 1; i >= 0; i--) {
      prefix_sum = prefix_sum + nums[i];
      max_sum = Math.max(prefix_sum - min_sofar, max_sum);
      maxFromRight[i] = max_sum;
      min_sofar = Math.min(min_sofar, prefix_sum);

      min_sum = Math.min(min_sum, prefix_sum - max_sofar);
      minFromRight[i] = min_sum;
      max_sofar = Math.max(max_sofar, prefix_sum);
    }

		// 合并
    int max_diff = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length - 1; i++) {
      max_diff = Math.max(max_diff, Math.abs(maxFromLeft[i] - minFromRight[i + 1]));
      max_diff = Math.max(max_diff, Math.abs(minFromLeft[i] - maxFromRight[i + 1]));
    }

    return max_diff;
  }
}
