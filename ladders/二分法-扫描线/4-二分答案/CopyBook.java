// 给定 n 本书, 第 i 本书的页数为 pages[i]. 现在有 k 个人来复印这些书籍, 而每个人只能复印编号连续的一段的书, 
// 比如一个人可以复印 pages[0], pages[1], pages[2], 但是不可以只复印 pages[0], pages[2], pages[3] 而不复印 pages[1].
// 所有人复印的速度是一样的, 复印一页需要花费一分钟, 并且所有人同时开始复印. 怎样分配这 k 个人的任务, 使得这 n 本书能够被尽快复印完?
// 返回完成复印任务最少需要的分钟数.

// 输入: pages = [3, 2, 4], k = 2  输出: 5
// 解释: 第一个人复印前两本书, 耗时 5 分钟. 第二个人复印第三本书, 耗时 4 分钟

// 思路：
// 二分答案解题方法
// 1. 找到可行解范围：分钟（假设都是最厚的书，k个人要几分钟？）
// 2. 猜答案       : 
// 3. 检验条件     : 检验能不能分完书
// 4. 调整搜索范围  ：
public class CopyBook {

  public int copyBooks(int[] pages, int k) {
		int max_pages = 0;
		int total_pages = 0;
		for (int page : pages) {
			max_pages = Math.max(max_pages, page);
			total_pages += page;
		}

		int upper = total_pages;
		int lower = max_pages;

		while (lower + 1 < upper) {
			int middle = lower + (upper - lower) / 2;
			if (canCopy(middle, k, pages)) upper = middle;
			else lower = middle;
		}

		return canCopy(lower, k, pages) ? lower : upper;
	}

	// 如何分书检查是此题关键
	private boolean canCopy(int time, int k, int[] pages) {
		int people_count = 1;
		int time_used = 0;
	
		for (int i = 0; i < pages.length; i++) {
			// 时间够复印下一个
			if (time_used + pages[i] <= time) {
				time_used += pages[i];
				continue;
			}	
			// 不够复印下一个，加人，更新time_used
			time_used = pages[i];
			people_count++;
		}
		
		return people_count <= k;
	}
}
