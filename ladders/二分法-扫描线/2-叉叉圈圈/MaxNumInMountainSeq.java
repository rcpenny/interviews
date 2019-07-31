// 给 n 个整数的山脉数组，即先增后减的序列，找到山顶（最大值）
// 输入: nums = [1, 2, 4, 8, 6, 3] 
// 输出: 8

public class MaxNumInMountainSeq {

  public int mountainSequence(int[] nums) {
    int start = 0, end = nums.length - 1;

    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      // key point middle 与 middle - 1比可能会 index out of bound
      if (nums[middle] < nums[middle + 1]) 
        start = middle;
      else 
        end = middle;
    }

    return Math.max(nums[start], nums[end]);
  }
}