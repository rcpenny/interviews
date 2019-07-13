// Input：[1,2,5] 3 [3,4] 2
// Output：[1,2,3,4,5]
// Explanation:
// After merge, A will be filled as [1, 2, 3, 4, 5]

public class MergeSortedArray {
  public void mergeSortedArray(int[] A, int m, int[] B, int n) {
		if (B == null || B.length == 0) return;
	
		int aptr = m - 1, bptr = n - 1, index = m + n - 1;

		// 思路：从新数组的尾部开始比较
		while (aptr >= 0 && bptr >= 0) {
			int a_tail = A[aptr];
			int b_tail = B[bptr];
			if (a_tail >= b_tail) { // 谁大谁fit in
				A[index] = a_tail;
				aptr--;
			} else {
				A[index] = b_tail;
				bptr--;
			}
			index--;
		}

		// 处理其中一个已经跑完的情况，B跑完其实不用处理，A就已经留在里面了
		while (bptr >= 0) A[index--] = B[bptr--]; 
	}
}
