import java.util.Arrays;

// 输入：nums = [3, 2, 4, 3, 5, 1]，sub = [2, 4]
// 输出：[2, 5] 
// 解释：<=2的数字[1,2]，<=4的数字[1,2,3,3,4]

// lint1791

public class SimpleQueries {
  public int[] simpleQueries (int[] nums, int[] sub) {
    int[] result = new int[sub.length];
    if (nums == null || nums.length == 0) return result;

    Arrays.sort(nums);

    for (int i = 0; i < sub.length; i++) {
      int count = query(nums, sub[i]);
      result[i] = count;
    }

    return result;
  }

  // find last position that <= target
  private int query(int[] nums, int target) {
    if (target >= nums[nums.length - 1]) return nums.length;
    if (target < nums[0]) return 0;

    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (nums[middle] <= target) start = middle;
      else end = middle;
    }

    if (nums[end] <= target) return end + 1;
    return start + 1;
  }
}