// 有一个消息包含A-Z通过以下规则编码
// 'A' -> 1 'B' -> 2 ... 'Z' -> 26
// 现在给你一个加密过后的消息，问有几种解码的方式

// 确定状态: 数字串前i个数字有f[i]种解密方式
// 转移方程: f[i] = f[i-1] if valid S[i-1] + f[i-2] if valid S[i-1]S[i-2] 
// 初态边界: f[0] = 1, i=1,只看最后一个数字
// 计算顺序: 左到右

public class DecodeWays {
  public int numDecodings(String ss) {
		if (ss == null || ss.length() == 0) return 0;

		char[] s = ss.toCharArray();
		int n = s.length;
		int[] f = new int[n + 1];

		f[0] = 1;

		for (int i = 1; i <= n; i++) { // 也可以先确定f[1] f[2] 从i=3开始
			f[i] = 0;

			// 看前一个数字
			if (array[i - 1] != '0') {
				f[i] += f[i - 1];
			}

			// 看前两个数字
			if (i > 1) {
				if (array[i - 2] == '1' || (array[i - 2] == '2' && array[i - 1] <= '6'))
				  f[i] += f[i - 2];
			}
		}

		return f[n];
	}
}
