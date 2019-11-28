// 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和
// 输入：[−2,2,−3,4,−1,2,1,−5,3]
// 输出：6
// 解释：符合要求的子数组为[4,−1,2,1]，其最大和为 6
// leet53

// o(n) o(1)
public class MaxiumSubarrayBest {
  public int maxSubArray(int[] nums) {
    int prefixSum = nums[0];
    int curMinSum = Math.min(0, nums[0]);
    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
      prefixSum += nums[i];
      max = Math.max(max, prefixSum - curMinSum);
      curMinSum = Math.min(curMinSum, prefixSum);
    }

    return max;
  }
}
