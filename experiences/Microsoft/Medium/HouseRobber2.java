// 这题是House Robber的扩展，只不过是由直线变成了圈

public class HouseRobber2 {
  public int houseRobber2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];

		int n = nums.length;
		int i;

		int[] f1 = new int[n + 1];
		f1[0] = 0;
		f1[1] = 0; // 第一栋房子不抢, 最后一栋可以抢或不抢

		for (i = 2; i <= n; i++) {
			f1[i] = f1[i - 1];
			f1[i] = Math.max(f1[i], f1[i - 2] + nums[i - 1]);
		}

		int[] f2 = new int[n + 1];
		f2[0] = 0;
		f2[1] = nums[0]; // 第一栋抢，最后一栋抢不了

		for (i = 2; i < n; i++) {
			f2[i] = f2[i - 1];
			f2[i] = Math.max(f2[i], f2[i - 2] + nums[i - 1]);
		}
		
		return Math.max(f1[n], f2[n - 1]);
	}
}
