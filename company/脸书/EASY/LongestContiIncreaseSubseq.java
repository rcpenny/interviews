// 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），
// 请找出该数组中的最长上升连续子序列。（最长上升连续子序列可以定义为从右到左或从左到右的序列。）

// https://www.lintcode.com/problem/longest-continuous-increasing-subsequence

// 因为是连续的所以不用开什么空间
public class LongestContiIncreaseSubseq {
  public int longestIncreasingContinuousSubsequence(int[] nums) {
    if (nums == null || nums.length == 0) return 0;

    int longest = 1;

    int cur_longest = 1;
    // left -> right
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) longest = Math.max(longest, ++cur_longest);
      else cur_longest = 1;
    }

    // right to left
    cur_longest = 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] > nums[i + 1]) longest = Math.max(longest, ++cur_longest);
      else cur_longest = 1;
    }

    return longest;
  }
}