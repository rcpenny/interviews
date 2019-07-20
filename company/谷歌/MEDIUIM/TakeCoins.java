// 有n个硬币排成一排，每次要你从最左边或者最右侧拿出一个硬币
// 总共拿k次，写一个算法，使能拿到的硬币的和最大
// 1 <= k <= n <= 100000
// 硬币的价值不大于10000

// 输入： list = [5,4,3,2,1,6], k = 3 
// 输出：15.
// 解释：从左边开始连取两个硬币,右边取一个即可

// 双指针
public class TakeCoins {
  public int takeCoins(int[] list, int k) {
		int sum = 0;
		
		for (int i = 0; i < k; i++)
			sum = sum + list[i];
		
		int max = sum;
		int left = k - 1;
		int right = list.length - 1;

		for (int i = 0; i < k; i++) {
			sum = sum + list[right--] - list[left--];
			max = Math.max(sum, max);
		}

		return max;
	}
}
