// 给定一个包含非负整数的数组，
// 你的任务是计算从数组中选出的可以制作三角形的三元组数目，如果我们把它们作为三角形的边长

//leet611 

// ask if duplicates counts?

// 1. brutal force O(n^3)
// 2. O(n^2*logN) sort first.. start with two smaller numbers to binary search to find larger numbers
// 3. O(n^2) sort first, larger number as axis, two smallers do two pointers scan

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
				// 短边相加大于最长边
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
