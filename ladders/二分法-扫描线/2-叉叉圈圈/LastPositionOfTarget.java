// Find the last position of a target number in a sorted array. 
// Return -1 if target does not exist.

public class LastPositionOfTarget {
  public int lastPosition(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;

    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (nums[middle] <= target) start = middle;
      else end = middle;
    }

    // 连续重复数字，先查end
    if (nums[end] == target) return end;
    if (nums[start] == target) return start;
    return -1;
  }
}