// Suppose a sorted array is rotated at some pivot unknown to you beforehand.
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
// You can assume no duplicate exists in the array.

//      5               O 
//    4               O
//  3       2       O       X
//        1               X

// First position <= Last Number
// (WRONG: First position <= or < First Number)

public class FindMinInRotatedSortedArray {
  public int findMin(int[] nums) {
    int tail = nums[nums.length - 1];
    int start = 0, end = nums.length - 1;
    
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (nums[middle] <= tail) end = middle;
      else start = middle;
    }

    return Math.min(nums[start], nums[end]);
  }
}