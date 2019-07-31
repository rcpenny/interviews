// Input:  [1, 2, 1, 3, 4, 5, 7, 6]
// Output:  1 or 6
// return the index of peek.

public class FindPeakElement {
  public int findPeak(int[] A) {
    if (A == null || A.length == 0) return -1;
    
    int start = 0, end = A.length - 1;

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
