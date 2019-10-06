// 给定一个包含非负整数的数组，
// 你的任务是计算从数组中选出的可以制作三角形的三元组数目，如果我们把它们作为三角形的边长

//lc611 

public class ValidTriangleNumber {
  public int triangleNumber(int[] A) {
    if (A == null || A.length < 3) return 0;

    Arrays.sort(A);
    
    int n = A.length;
    
    int count = 0;
    
    // i 从大到小
    for (int i = n - 1; i >= 2; i--) {
      int left = 0;
      int right = i - 1;
      
      while (left < right) {
        if (A[left] + A[right] > A[i]) {
          count = count + (right - left);
          right--;
        } else {
          left++;
        }
      }
    }

    return count;
  }
}
