import java.util.Comparator;

// 给一定数量的信封，带有整数对 (w, h) 分别代表信封宽度和高度。
// 一个信封的宽高均大于另一个信封时可以放下另一个信封。
// 求最大的信封嵌套层数。
// 输入： [[5,4],[6,4],[6,7],[2,3]]    输出：3
// 解释： 最大的信封嵌套层数是 3 ([2,3] => [5,4] => [6,7])

// 1. 确定状态: f[i] 在位置i, 能放最多的信
// 2. 转移方程：f[i] = max{1, f[j] + 1} (j < i且 j.w < i.w && j.h < i.h)
// 3. 初态边界: f[0] = 1??
// 4. 顺序计算: envelopes左至右，sorted

public class RussianDollEnvelope {
	
	// 先比width，再比height
	private Comparator<int []> cpt = new Comparator<int[]>() {
		@Override public int compare(int[] a, int[] b) {
			if (a[0] != b[0]) return a[0] - b[0];
			return a[1] - b[1];
		}};

  public int maxEnvelopes(int[][] envelopes) {
		int n = envelopes.length;
		if (n == 0) return 0;
	
		Arrays.sort(envelopes, cpt);
		int[] f = new int[n];

		int max_envelopes = 0;
		for (int i = 0; i < n; i++) {
			f[i] = 1;
			int[] current = envelopes[i];
			
			// 用二分法提升至nlogn, find last position of fit in envelopes
			// 这里是暴力循环所有前面的f[j]了
			for (int j = 0; j < i; j++) {
				int[] prev = envelopes[j];
				if (prev[0] < current[0] && prev[1] < current[1])
					f[i] = Math.max(f[i], f[j] + 1);
			}

			max_envelopes = Math.max(max_envelopes, f[i]);
		}

		return max_envelopes;
	}
}
