//同上题，但是contain dupes

public class FindMinInRotatedSortedArray2 {
  public int findMin(int[] nums) {
    int tail = nums[nums.length - 1];
    int head = nums[0];

    // 多考虑一个首位同数的情况，导致第一次二分你无法判断在哪个区间.
    if (tail == head) {
      for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i + 1] < nums[i]) return nums[i + 1];
      }
      return tail;
    }

    int start = 0, end = nums.length - 1;
    
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (nums[middle] <= tail) end = middle;
      else start = middle;
    }

    return Math.min(nums[start], nums[end]);
  }
}