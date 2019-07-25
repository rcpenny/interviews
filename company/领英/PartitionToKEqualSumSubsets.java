import java.util.Arrays;

// 给定一个整数数组nums和一个正整数k，是否有可能能够把这个数组分成k个非空的子集，满足子集的权重全部都相等。
// 输入: nums = [4, 3, 2, 3, 5, 2, 1] 和 k = 4   输出: True
// 解释:  一个可能的划分 (1, 2, 3), (1, 5), (3, 3)，拥有相等的权重

// 这是一个组合问题
public class PartitionToKEqualSumSubsets {
	private boolean result = false;

  public boolean partitiontoEqualSumSubsets(int[] nums, int k) {
		int sum = Arrays.stream(nums).sum();
		if (sum % k != 0) return false;
		int target = sum / k;

		return result;
	}
}
