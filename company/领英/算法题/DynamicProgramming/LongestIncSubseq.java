// 给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度
// 输入: [4,2,4,5,3,7]  输出:  4   LIS 是 [2,4,5,7]

// 1. 确定状态： f[i] 以 a[i] 为结尾的最长上升子序列
// 2. 转移方程： f[i] = max {1, f[i] + 1 (i < j && a[i] < a[j])}
// 3. 始态边界： f[i] is assigned with 1 first
// 4. 顺序计算： 0 —> n

// leet300

/*
	O(nlogn)解法：

	使用一个辅助空间B数组。
	B[i]存储Dp值为i的最小的数字。（有多个位置，以这些位置为结尾的LIS长度都为i， 则这些数字中最小的一个存在B[i]中）
	则B数组严格递增。且下标表示LIS长度，也是严格递增，可以在B数组中进行二分查找。

	对于每个位置i，我们要找，所有小于A[i], 且Dp值最大的那个。这个操作在B数组中二分查找
*/

// 时间复杂度：O(nlog n)
// 空间复杂度：O(n)

public class LongestIncSubseq {
  public int lengthOfLIS(int[] nums) {
    int n = nums.length;

    int[] f = new int[n];
    
    int len = 0;

    for (int num : nums) {
      // public static int binarySearch(byte[] a, int fromIndex, int toIndex, byte key)
      int i = Arrays.binarySearch(f, 0, len, num);
      
      if (i < 0) {
        i = -(i + 1);
      }

      f[i] = num;
      
      if (i == len) {
        len++;
      }
    }
    
    return len;
  }
}
