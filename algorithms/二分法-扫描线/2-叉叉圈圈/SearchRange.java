public class SearchRange {
  public int[] searchRange(int[] A, int target) {
    int[] result = {-1, -1};
    if (A == null || A.length == 0) return result;
    
    // find first position
    int start = 0, end = A.length - 1;
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (A[middle] >= target) end = middle;
      else start = middle;
    }
    // case where target not in A
    if (A[start] == target) result[0] = start;
    else if (A[end] == target) result[0] = end;
    else return result;
    
    int start_ = 0, end_ = A.length - 1;
    while (start_ + 1 < end_) {
      int middle_ = start_ + (end_ - start_) / 2;
      if (A[middle_] <= target) start_ = middle_;
      else end_ = middle_;
    }
    
    if (A[end_] == target) result[1] = end_;
    else if (A[start_] == target) result[1] = start_;
    
    return result;
  }
}
