// n把钥匙（编号1-n），m把锁（编号1-m），当锁的号码能够被钥匙的号码整除的时候，该锁就可以被打开/关闭。
// 最初所有的锁都是锁住的状态，然后用所有的钥匙去开所有的锁，返回有几把锁最后是打开的。

// 输入: n = 2, m = 5 输出: 3
// 解释: 编号为1,3,5的锁被打开了

// TLE
public class UnlockProblem {
  public int unlock(int n, int m) {
		boolean[] locks = new boolean[m];

		for (int i = 0; i < n; i++)
			for (int j = i; j < m; j = j++)
				if ((j + 1) % (i + 1) == 0) 
				  locks[j] = !locks[j];

		int count = 0;
		for (boolean lock : locks)
			if (lock) count++;

		return count;
	}

	// 如果被开了，就锁起来，如果锁了就打开， 0^1 = 1, 1^ 1= 0
	public int unlock2(int n, int m) {
		int[] lock = new int[m + 1];
		for (int i = 1; i <= n; i++) {
				int j = i;
				while (j < lock.length) {
						lock[j] ^= 1;
						j += i;
				}
		}
		return Arrays.stream(lock).sum();
	}
}
