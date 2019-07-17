import java.util.HashSet;
import java.util.Set;

//  Giving a string with number from 1-n in random order, 
//  but miss 1 number.Find that number.
// Input: n = 20 and str = 19201234567891011121314151618
// Output: 17

public class FindMissingNumber2 {
	public int missing = -1;

  public int findMissing2(int n, String str) {
		if (n <= 0) return missing;

		boolean[] found = new boolean[n + 1];
		find(str, n, 0, 0, found);

		return missing;
	}
	
	// 定义：元数据str，n   控制：index, count   当前状态：visisted
	private void find(String str, int n, int index, int count, boolean[] found) {
		// 出口: count/index达到极值
		if ( count == n - 1 && index == str.length())
			for (int i = 1; i < found.length; i++) if (!found[i]) missing = i;
		if (count >= n - 1 || index >= str.length()) return;

		// 拆解: 一位数，两位数
		for (int len = 1; len <= 2; len++) {
			if (index + len > str.length()) continue; // 进入递归的条件：index不越界，数字不合法
			String sub = str.substring(index, index + len);
			if (!isValidNumber(sub, n, found)) continue;
			
			int val = Integer.valueOf(sub);
			found[val] = true;
			find(str, n, index + len, count + 1, found);
			found[val] = false;
		}
	}

	// 数字合理（1 ～ n）且未被visit过
	private boolean isValidNumber(String sub, int n, boolean[] found) {
		if (sub.charAt(0) == '0') return false; // 去掉 "0", "06" 这样的case
		int val = Integer.valueOf(sub);
		return (val > 0 && val <= n && !found[val]) ? true : false;
	}
}
