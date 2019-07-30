// 给定 n 本书, 第 i 本书的页数为 pages[i]. 
// 现在有 k 个人来复印这些书籍, 而每个人只能复印编号连续的一段的书, 
// 所有人复印的速度是一样的, 复印一页需要花费一分钟, 并且所有人同时开始复印. 
// 返回完成复印任务最少需要的分钟数.

// 输入: pages = [3, 2, 4], k = 2  输出: 5
// 解释: 第一个人复印前两本书, 耗时 5 分钟. 第二个人复印第三本书, 耗时 4 分钟

// 思路：二分答案解题方法
// 1. 找到可行解范围：最厚的一本书 -> 所有页数
// 3. 检验条件     : 检验能不能分完书

public class CopyBook {

  public int copyBooks(int[] pages, int k) {
		int lower = 0; // lower minutes
		int upper = 0; // upper minutes
		for (int page : pages) {
			lower = Math.max(lower, page);
			upper += page;
		}

		while (lower + 1 < upper) {
			int middle = lower + (upper - lower) / 2;
			if (kPeopleCanCopy(middle, k, pages)) upper = middle; // 说明时间还有缩小的余地
			else lower = middle;
		}

		return kPeopleCanCopy(lower, k, pages) ? lower : upper;
	}

	// k个人能否在time分钟抄完
	private boolean kPeopleCanCopy(int time, int k, int[] pages) {
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
