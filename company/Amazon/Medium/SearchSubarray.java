import java.util.HashMap;
import java.util.Map;

// 给定一个数组arr和一个非负整数k，你需要从这个数组中找到一个连续子数组，使得这个子数组的和为k
// 返回这个子数组的长度。如果有多个这样的子串，返回结束位置最小的，如果还有多个，返回起始位置最小的
// 如果找不到这样的子数组，返回-1

// FB round 1 Q1
public class SearchSubarray {
  public int searchSubarray(int[] arr, int k) {
    Map<Long, Integer> sumToIndex = new HashMap<>();
    long prefix_sum = 0;
    for (int i = 0; i < arr.length; i++) {
      prefix_sum = prefix_sum + (long) arr[i];
      if (sumToIndex.containsKey(prefix_sum - (long) k)) {
        return i + 1 - sumToIndex.get(prefix_sum - (long) k);
      }
      sumToIndex.put(prefix_sum, i + 1);
    }
    return -1;
  }
}