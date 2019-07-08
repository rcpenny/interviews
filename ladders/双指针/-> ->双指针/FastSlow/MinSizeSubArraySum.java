// 给定一个由 n 个正整数组成的数组和一个正整数 s ，
// 请找出该数组中满足其和 ≥ s 的最小长度子数组。如果无解，则返回 -1

public class MinSizeSubArraySum {
  public int minimumSize(int[] nums, int s) {
    int current_sum = 0;
    int fast = 0, slow = 0;
    int min_size = Integer.MAX_VALUE;
  
    for (slow = 0; slow < nums.length; slow++) {

      while (fast < nums.length && current_sum < s) {
        current_sum = current_sum + nums[fast];
        fast++;
      }

      if (current_sum >= s) {
        min_size = Math.min(min_size, fast - slow + 1);
      }
      // bug点，把这个写在上面的if里了，但是这个减法操作每次都有啊
      current_sum = current_sum - nums[slow];
    }

    return min_size == Integer.MAX_VALUE ? -1 : min_size;
  }
}