// 给定 n 本书, 每本书具有相同的页数. 现在有 k 个人来复印这些书. 
// 其中第 i 个人需要 times[i] 分钟来复印一本书.
// 每个人可以复印任意数量的书. 怎样分配这 k 个人的任务, 使得这 n 本书能够被尽快复印完?
// 返回完成复印任务最少需要的分钟数.

// 输入: n = 4, times = [3, 2, 4]   输出: 4
// 解释: 第一个人复印 1 本书, 花费 3 分钟. 第二个人复印 2 本书, 花费 4 分钟. 第三个人复印 1 本书, 花费 4 分钟.

// 确定答案范围 + 验证答案大小
// 答案：总共需要多少时间
public class CopyBook2 {
  public int copyBooksII(int n, int[] times) {
		int max_time = Integer.MIN_VALUE;
		for (int time : times) max_time = Math.max(max_time, time);

		int lower = 0;
		int upper = max_time * n;

		while (lower + 1 < upper) {
			int mid = lower + (upper - lower) / 2;
			if (canFinish(times, n, mid)) upper = mid;
			else lower = mid;
		}

		return canFinish(times, n, lower) ? lower : upper;
	}

	private boolean canFinish(int[] times, int n, int total) {
		int books_count = 0;
		for (int time : times)
			books_count += total / time;
		return books_count >= n;
	}
}
