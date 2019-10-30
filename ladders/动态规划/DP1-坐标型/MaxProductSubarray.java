// 找出一个序列中乘积最大的连续子序列（至少包含一个数）
// 输入:[2,3,-2,4] 输出:6

// 确定状态: p[i] 在i位置的最大正积，n[i] 在i位置的最大负积
// 转移方程: 根据正负情况判断，max, min一下
// 初态边界: p[0] = nums[0] or n[0] = 0
// 计算顺序: 0 -> len

// leet152
public class MaxProductSubarray {
  public int maxProduct(int[] nums) {
    if (nums.length == 1) return nums[0];
    
    int n = nums.length;
    
    // 可以优化至两个数组O(1)
    int[] pos = new int[n];
    int[] neg = new int[n];
    
    if (nums[0] > 0) pos[0] = nums[0];
    else neg[0] = nums[0];
    
    int max = pos[0];
    
    for (int i = 1; i < n; i++) {
      int cur = nums[i];
      
      if (cur == 0) {
        pos[i] = 0;
        neg[i] = 0;
      }
      
      // 注意 pos[i - 1] 或者 neg[i - 1]是0 带来的影响
      else if (cur > 0) {
        pos[i] = Math.max(pos[i - 1] * cur, cur);
        neg[i] = neg[i - 1] * cur;
      }
      
      else if (cur < 0) {
        pos[i] = neg[i - 1] * cur;
        neg[i] = Math.min(pos[i - 1] * cur, cur);
      }
      
      max = Math.max(max, pos[i]);
    }
    
    return max;
  }

  // old_pos = pos[i - 1]
  // old_neg = neg[i - 1]
  // new_pos = pos[i]
  // new_neg = neg[i]
  public int maxProduct2(int[] nums) {
    if (nums.length == 1) return nums[0];

    int old_pos = Math.max(0, nums[0]);
    int old_neg = Math.min(0, nums[0]);

    int new_pos = old_pos;
    int new_neg = old_neg;

    int max = new_pos;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == 0) {
        new_pos = 0;
        new_neg = 0;
      }

      else if (nums[i] > 0) {
        new_pos = Math.max(old_pos * nums[i], nums[i]);
        new_neg = Math.min(old_neg * nums[i], 0);
      }

      else if (nums[i] < 0) {
        new_pos = Math.max(old_neg * nums[i], 0);
        new_neg = Math.min(old_pos * nums[i], nums[i]);
      }

      max = Math.max(max, new_pos);
      old_pos = new_pos;
      old_neg = new_neg;
    }

    return max;
  }
}
