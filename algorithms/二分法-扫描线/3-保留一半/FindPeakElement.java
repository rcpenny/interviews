// Input:  [1, 2, 1, 3, 4, 5, 7, 6]
// Output:  1 or 6
// return the index of peek.

public class FindPeakElement {
  public int findPeak(int[] A) {
		if (A == null || A.length == 0) return -1;
		if (A.length == 1) return A[0];
    
    int start = 0, end = A.length - 1;

		// 不用担心 mid + 1, mid - 1越界, 因为start + 1 < end保证 start与end之间存在至少1个数字的buffer可以赋值给middle
    while (start + 1 < end) {
        int middle = start + (end - start) / 2;
        if (A[middle] > A[middle - 1] && A[middle] < A[middle + 1]) // 上升线
            start = middle;
        else if (A[middle] < A[middle - 1] && A[middle] > A[middle + 1]) // 谷底
            end = middle;
        else if (A[middle] < A[middle - 1] && A[middle] < A[middle + 1]) // 下降线
            end = middle;
        else
            return middle;
    }
    
    return A[end] > A[start] ? end : start;
  }
}
