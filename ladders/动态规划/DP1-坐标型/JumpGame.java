// 给出一个非负整数数组，你最初定位在数组的第一个位置。　　　
// 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　　
// 判断你是否能到达数组的最后一个位置

// 输入 : [2,3,1,1,4]
// 输出 : true

// 存在性DP

// 1. 确定状态 f(i) = 能不能跳到index i
// 2. 转移方程 f(i) = (f(j) and j + a[j] >= i)  (j < i)
// 3. 初态边界 f(0) = true
// 4. 顺序计算 从小到大

// 时间复杂度 = 状态数 * 状态转移代价 (n * n)

public class JumpGame {
  public boolean canJump(int[] nums) {
    int n = nums.length;
    
    boolean[] f = new boolean[n];
    f[0] = true;
    
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (!f[j] || nums[j] + j < i) continue;
        f[i] = true;
        break;
      }
    }
    
    return f[n - 1];
  }
}
