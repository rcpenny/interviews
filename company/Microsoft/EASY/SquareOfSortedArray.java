// Input: [-4,-1,0,3,10]
// Output: [0,1,9,16,100]

public class SquareOfSortedArray {
  public int[] sortedSquares(int[] nums) {
    int[] square = new int[nums.length];

    for (int i = 0; i < nums.length; i++) nums[i] = Math.abs(nums[i]);

    int start = 0, end = nums.length - 1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[start] > nums[end]) {
        square[nums.length - 1 - i] = nums[start] * nums[start];
        start++;
        continue;
      }
      square[nums.length - 1 - i] = nums[end] * nums[end];
      end--;
    }

    return square;
  }
}