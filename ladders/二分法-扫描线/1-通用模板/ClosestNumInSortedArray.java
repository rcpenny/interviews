// Given a target number and an integer array A sorted in ascending order, 
// find the index i in A such that A[i] is closest to the given target.
// Return -1 if there is no element in the array.

public class ClosestNumInSortedArray {
  public int closestNumber(int[] A, int target) {
    if (A == null || A.length == 0) return -1;

    int start = 0, end = A.length - 1;
    
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (A[middle] < target) 
        start = middle;
      else if (A[middle] > target) 
        end = middle;
      else return middle;
    }
    
    return Math.abs(A[start] - target) <= Math.abs(A[end] - target) ? start : end;
  }
}