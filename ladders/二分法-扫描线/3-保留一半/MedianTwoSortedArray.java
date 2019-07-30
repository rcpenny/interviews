// 两个排序的数组A和B分别含有m和n个数，找到两个排序数组的中位数，要求时间复杂度应为O(log (m+n))。

// 如果有数组中有n个数且n是奇数，则中位数为A[(n-1)/2]A[(n−1)/2]。
// 如果有数组中有n个数且n是偶数，则中位数为 (A[n / 2] + A[n / 2 + 1]) / 2(A[n/2]+A[n/2+1])/2.

// 输入:
// A = [1,2,3,4,5,6]
// B = [2,3,4,5]
// 输出: 3.5

// 输入:
// A = [1,2,3]
// B = [4,5]
// 输出: 3

// 二分答案的方法，时间复杂度 O(log(range) * (log(n) + log(m)))O(log(range)∗(log(n)+log(m)))
// 其中 range 为最小和最大的整数之间的范围。
// 可以拓展到 Median of K Sorted Arrays

public class MedianTwoSortedArray {
  public double findMedianSortedArrays(int[] A, int[] B) {
		int L = A.length + B.length;

		if (L % 2 == 1) return kthLargest(A, B, L / 2 + 1) * 1.0;
	
		int a = kthLargest(A, B, L / 2); // 第L/2个, index L/2 - 1
		int b = kthLargest(A, B, L / 2 + 1);
		return (a + b) / 2.0;
	}



	private int kthLargest(int[] A, int[] B, int k) {
		if (A.length == 0) return B[k - 1];
		if (B.length == 0) return A[k - 1];

		// 二分答案
		int lower = Math.min(A[0], B[0]);
		int upper = Math.max(A[A.length - 1], B[B.length - 1]);

		// 找到第一个x，A与B中有 >= k个数字 <= x
		while (lower + 1 < upper) {
			int mid = lower + (upper - lower) / 2;
			if (countSmallerOrEqual(A, mid) + countSmallerOrEqual(B, mid) < k)
				lower = mid;
			else
				upper = mid;
		}

		if (countSmallerOrEqual(A, start) + countSmallerOrEqual(B, start) >= k)
			return lower;
		return upper;
	}



	// 找这个数组中<=number的个数
	private int countSmallerOrEqual(int[] array, int number) {
		int start = 0, end = array.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] <= number) start = mid;
			else end = mid;
		}

		if (array[start] > number) return start;
		if (array[end] > number) return end;

		return array.length;
	}
}
