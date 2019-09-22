// Given a target number and an integer array sorted in ascending order. 
// Find the total number of occurrences of target in the array.
// XXXXOOOOOXXXXX

public class TotalOccurOfTarget {
  public int totalOccurrence(int[] A, int target) {
    if (A == null || A.length == 0 || A[0] > target || A[A.length - 1] < target) return 0;
    
    int firstPosition = 0, lastPosition = 0;  
    
    // find first position
    int start = 0, end = A.length - 1;
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (A[middle] >= target) end = middle;
      else start = middle;
    }
    firstPosition = (A[start] == target) ? start : end;

    // find last position
    int start_ = 0, end_ = A.length - 1;
    while (start_ + 1 < end_) {
      int middle = start_ + (end_ - start_) / 2;
      if (A[middle] <= target) start_ = middle;
      else end_ = middle;
    }
    lastPosition = (A[end_] == target) ? end_ : start_;
    
    return lastPosition - firstPosition + 1;
  }
}
