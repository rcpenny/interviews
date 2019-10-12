// 输入: [4, 5, 1, 2, 3] and target=1
// 输出: 2  你可以假设数组中不存在重复的元素

public class SearchInRotatedSortedArray {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;
    
    int tail = nums[nums.length - 1];
    int start = 0, end = nums.length - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) return mid;

      // 1. 先target与tail比，决定target在哪条线
      // 2. 画线

      // target在大数线
      if (target > tail) {
        if (nums[mid] > target || nums[mid] <= tail) end = mid;
        else start = mid;
        continue;
      }

      // target在小数线
      if (target <= tail) {
        if (nums[mid] < target || nums[mid] > tail) start = mid;
        else end = mid;
      }
    }

    if (nums[start] == target) return start;
    if (nums[end] == target) return end;
    return -1;
  }
}
// 如果有dupes 这个问题在面试中不会让实现完整程序
// 只需要举出能够最坏情况的数据是 [1,1,1,1... 1] 里有一个0即可。
// 在这种情况下是无法使用二分法的，复杂度是O(n)
// 因此写个for循环最坏也是O(n)，那就写个for循环就好了
//  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
//  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
