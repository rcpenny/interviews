// 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。
// 你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。

// 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱
// lint392

// 1. 确定状态 f[i] 在前i个house的位置能抢到最多的钱
// 2. 转移方程 f[i] = max(f[i - 2] + A[i], f[i - 1])
// 3. 初态边界 f[0] = 0, f[1] = A[1]
// 4. 顺序计算 f[2] -> f[n]

// 序列型
public class HouseRobber {
  public long houseRobber(int[] A) {
    int n = A.length;
    if (n == 0) return 0;

    long[] f = new long[n + 1];
    f[0] = 0;
    f[1] = A[0];

    for (int i = 2; i <= n; i++) {        // 第i个房子的价值
      f[i] = Math.max(f[i - 1], f[i - 2] + A[i - 1]);
    } 

    return f[n];
	}
	
	// O(n) -> O(1)
	public int robOp(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    
    int n = nums.length;

    int first = 0;
    int second = nums[0];
    int third = second;

    for (int i = 2; i <= n; i++) {
      third = Math.max(second, first + nums[i - 1]);
      first = second;
      second = third;
    }

    return third;
  }
}
