/**
 * Given an array of n integers, and a moving window(size k), 
 * move the window at each iteration from the start of the array, 
 * find the sum of the element inside the window at each moving.
 */
public class WindowSum {

  public int[] winSum(int[] nums, int k) {
    if (k > nums.length || k <= 0) return new int[0];
    int[] sum = new int[nums.length - k + 1];

    for (int i = 0; i < k; i++) sum[0] += nums[i];   
    for (int i = k; i < nums.length; i++) {
      sum[i - k + 1] = sum[i - k] - nums[i - k] + nums[i];
    }
    return sum;
  }
}