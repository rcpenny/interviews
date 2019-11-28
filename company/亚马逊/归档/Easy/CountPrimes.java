public class CountPrimes {
	public int countPrimes(int n) {
		if (n <= 1) return 0;

		boolean[] not_prime = new boolean[n + 1];
		int count = 0;

		// 非常机智的解法
		// 3 * 7 = 21, 3 * 8 = 24.
		// i 是 尝试数 3，7 是 倍数 增量。
		for (int i = 2; i < n; i++) {
			if (!not_prime[i]) count++;

			for (int j = 2; i * j < n; j++) {
				not_prime[i * j] = true;
			}
		}

		return count;
	}
}
