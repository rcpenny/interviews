import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 一个非负数可以被视为其因数的乘积。编写一个函数来返回整数 n 的因数所有可能组合。
 * 组合中的元素(a1,a2,...,ak)必须是非降序。(即，a1≤a2≤...≤ak)。
 * 结果集中不能包含重复的组合。
 * 
 * 输入：8
 * 输出： [[2,2,2],[2,4]]
 * 解释： 8 = 2 x 2 x 2 = 2 x 4
 */

public class Factorization {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<>();

		factorize(n, 2, new ArrayList<>(), result);
		
		return result;
	}

	private void factorize(int n, int start, List<Integer> comb, List<List<Integer>> result) {
		// 避免n的情况 8 -> [8]
		if (n == 1 && comb.size() > 1) {
			result.add(new ArrayList<>(comb));
		}
		if (n <= 1) return;

		// 有顺序，从start开始，到 n结束
		for (int i = start; i <= n; i++) {
			if (n % i != 0) continue;

			comb.add(i);
			factorize(n / i, i, comb, result);
			comb.remove(comb.size() - 1);
		}
	}
}
