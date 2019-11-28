// 给定 n 本书, 每本书具有相同的页数. 现在有 k 个人来复印这些书. 
// 其中第 i 个人需要 times[i] 分钟来复印一本书.
// 每个人可以复印任意数量的书. 怎样分配这 k 个人的任务, 使得这 n 本书能够被尽快复印完?
// 返回完成复印任务最少需要的分钟数.

// 输入: n = 4, times = [3, 2, 4]   输出: 4
// 解释: 第一个人复印 1 本书, 花费 3 分钟. 第二个人复印 1 本书, 花费 2 分钟. 第三个人复印 1 本书, 花费 4 分钟.

// 答案：总共需要多少时间
// 二分范围：0 ~ 最慢的人，复印所有书的时间

public class CopyBook2 {

  public int copyBooksII(int n, int[] times) {
		// 复印一本书最慢的人，要多久
		int max_time = 0;
		for (int time : times) 
			max_time = Math.max(max_time, time);

		int lower = 0;
		int upper = max_time * n; // 最慢的人，一个人复印所有书

		while (lower + 1 < upper) {
			int mid = lower + (upper - lower) / 2;
			if (booksCopied(times, mid) >= n) 
				upper = mid;
			else 
				lower = mid; // 复印不完n本书，加时间
		}

		return canFinish(times, n, lower) ? lower : upper;
	}

	// 计算给这么多时间，这么多人能复印多少书
	private int booksCopied(int[] times, int limit) {
		int books_finished = 0;

		for (int time : times)
			books_finished += limit / time;
		
		return books_finished;
	}
}
