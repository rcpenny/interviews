// 给一个目标数 target, 一个非负整数 k, 一个按照升序排列的数组 A。
// 在A中找与target最接近的k个整数。返回这k个数并按照与target的接近程度从小到大排序
// 如果接近程度相当，那么小的数排在前面。

public class FindKClosestElements {
  public int[] kClosestNumbers(int[] a, int target, int k) {
    int[] closet = new int[k];
    if (a == null || a.length == 0 || k == 0) return closet;

		int start = 0, end = a.length - 1;

		// 这里==target无所谓mid选end还是start,总归最后双指针会解决
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (a[middle] >= target) end = middle;
      else start = middle;
    }

    // 以start与end作为背向指针 找k个接近值
    for (int i = 0; i < k; i++) {
      // 特殊情况，start,end到头了，直接append另一个
      if (start < 0) {
        closet[i] = a[end++];
        continue;
      }
      if (end >= a.length) {
        closet[i] = a[start--];
        continue;
      }

      int diff_start = Math.abs(a[start] - target);
      int diff_end = Math.abs(a[end] - target);
      if (diff_start <= diff_end) 
        closet[i] = a[start--];
      else 
        closet[i] = a[end++];
    }

    return closet;
  }
}
