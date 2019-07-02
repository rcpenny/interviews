/**
 * 给一个由 1 - n 的整数随机组成的一个字符串序列，其中丢失了一个整数，请找到它
 * 
 * 输入: n = 6 和 str = 56412
		输出: 3
		解释:
		5'6'4'1'2
 */
public class FindMissingNumber2 {
	private int missing = -1;

  public int findMissing2(int n, String str) {
		if (n < 1 || str == null) return -1;
		find(str, 0, 0, n, new boolean[n + 1]);
		return missing;
	}

	private void find(String str, int index, int num, int n, boolean[] states) {
		if (index == str.length() && num == n - 1) {
		  for (int i = 1; i <= n; i++) {
		    if (!states[i]) missing = i;
		  }
			return;
		}
    
    // n <= 30, so max 2 digits
		for (int len = 1; len <= 2; len++) {
		  if (index + len > str.length()) continue;
		  
			String sub = str.substring(index, index + len);
			if (!isValidNumber(n, sub, states)) continue;

      int val = Integer.valueOf(sub);
			states[val] = true;
			find(str, index + len, num + 1, n, states);
			states[val] = false;
		}
	}

	private boolean isValidNumber(int n, String sub, boolean[] states) {
	  // skip "06" case
	  if (sub.charAt(0) == '0') return false;
	  int val = Integer.valueOf(sub);
	  return (val < 1 || val > n || states[val]) ? false :  true;
	}
}
