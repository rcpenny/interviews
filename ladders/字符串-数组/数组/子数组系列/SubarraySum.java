import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 给定一个整数数组，找到和为零的子数组。
// 你的代码应该返回满足要求的子数组的起始位置和结束位置

public class SubarraySum {
  public List<Integer> subarraySum(int[] nums) {
    List<Integer> results = new ArrayList<>();

    Map<Integer, Integer> sumToIndex = new HashMap<>();
    int prefix_sum = 0;
    for (int i = 0; i < nums.length; i++) {
      prefix_sum = prefix_sum + nums[i];
      int target = 0 - prefix_sum;
      // 就是个two sum变种，注意先查complement是否存在，再加入当前的prefix_sum
      if (sumToIndex.containsKey(target)) {
        results.add(sumToIndex.get(target));
        results.add(i);
        break;
      }
      sumToIndex.put(prefix_sum, i);
    }

    return results;
  }
}