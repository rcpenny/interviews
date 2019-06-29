import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，找到两个数的 差 等于目标值。
 * index1必须小于index2。注意返回的index1和index2不是 0-based。
 */
public class TwoSumDiffEqualsTarget {

  public int[] twoSum7(int[] nums, int target) {
		Map<Integer, Integer> numToIndex = new HashMap<>();
		int[] pair = new int[2];

		for (int i = 0; i < nums.length; i++) {
			int cur = nums[i];
			if (numToIndex.containsKey(cur + target)) {
				pair[0] = numToIndex.get(cur + target);
				pair[1] = i;
				break;
			}
			if (numToIndex.containsKey(cur - target)) {
				pair[0] = numToIndex.get(cur - target);
				pair[1] = i;
				break;
			}
			numToIndex.put(cur, i);
		}

		return pair;
	}
}
