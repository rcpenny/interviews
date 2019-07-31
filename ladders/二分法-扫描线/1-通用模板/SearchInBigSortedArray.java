// Given a big sorted array with non-negative integers sorted by non-decreasing order. 
// The array is so big so that you can not get the length of the whole array directly, 
// and you can only access the kth number by ArrayReader.get(k)
// Find the first index of a target number. Your algorithm should be in O(log k), 
// where k is the first index of the target number.
// Return -1, if the number doesn't exist in the array.

public class SearchInBigSortedArray {
  public int searchBigSortedArray(ArrayReader reader, int target) {
    if (target < reader.get(0)) return -1;

    // exponential to get upper bound
    int end = 1;
    while (reader.get(end) < target) 
      end = end * 2;

    int start = end / 2;

    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (reader.get(middle) >= target) // 1st position of target
        end = middle;
      else 
        start = middle;
    }

    if (reader.get(start) == target) return start;
    if (reader.get(end) == target) return end;

    return -1;
  }
}