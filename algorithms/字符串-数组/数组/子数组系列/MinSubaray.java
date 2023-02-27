import java.util.List;

// 给定一个整数数组，找到一个具有最小和的连续子数组。返回其最小和
// 输入：[1, -1, -2, 1]  输出：-3

public class MinSubaray {
  public int minSubArray(List<Integer> nums) {
		int cur_sum = 0;
		int max_sum_sofar = 0;
		int min_sum = Integer.MAX_VALUE;
		
		for (Integer i : nums) {
			cur_sum += i;
			min_sum = Math.min(min_sum, cur_sum - max_sum_sofar);
			max_sum_sofar = Math.max(max_sum_sofar, cur_sum);
		}
		
		return min_sum;
	}
}
