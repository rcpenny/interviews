// 给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度
// 输入: [4,2,4,5,3,7]  输出:  4   LIS 是 [2,4,5,7]

// 1. 确定状态： f[i] 以 a[i] 为结尾的最长上升子序列
// 2. 转移方程： f[i] = max {1, f[i] + 1 (i < j && a[i] < a[j])}
// 3. 始态边界： f[i] is assigned with 1 first
// 4. 顺序计算： 0 —> n

// O（N*N)
public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0)
      return 0;
    
    int n = nums.length;
    
    int[] f = new int[n];
    int max = 0;
    
    for (int i = 0; i < n; i++) {
      f[i] = 1; // self start as subseq
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j])
          f[i] = Math.max(f[i], f[j] + 1);
      }
      max = Math.max(max, f[i]);
    }
    
    return max;
  }
}
