// Given an array with positive and negative numbers, 
// find the maximum average subarray which length should be greater or equal to given length k.

// Input:  [1,12,-5,-6,50,3] 3
// Output: 15.667

// 如果最大平均值是T
// 那么 (A[left] + ... + A[right]) / (right - left + 1) >= T
// 转换成 (A[left] - T) + ... + (A[right] - T) >= 0     right - left + 1 >= k
 
@Todo("此题前缀和暴力可解，更好的是用二分答案")
public class MaxAvgSubarray2 {
	public double maxAverage(int[] nums, int k) {
		
	}
}
