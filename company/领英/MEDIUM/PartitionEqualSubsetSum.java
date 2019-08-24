// 给一 只含有正整数 的 非空 数组, 找到这个数组是否可以划分为 两个 元素和相等的子集

// 用dp解，背包问题
// 能否背到总价值的一半。
// 01背包即可。

public class PartitionEqualSubsetSum {
  private boolean result = false;

  public boolean canPartition(int[] nums) {
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

    for (int i = index; i < nums.length; i++) {
      find(nums, index + 1, sum, cur_sum + nums[i]);
    }
  }
}