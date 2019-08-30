// Input:
// A = [1,2,3,0,0,0], m = 3
// B = [2,5,6],       n = 3

// Output: [1,2,2,3,5,6]
// leet88

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
		while (bptr >= 0) {
			A[index--] = B[bptr--];
		}

		return;
	}
}
