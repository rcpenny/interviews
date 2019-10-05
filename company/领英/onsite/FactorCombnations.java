import java.awt.List;
import java.util.ArrayList;

// 数字可以被视为其因数的乘积。 例如，
// 输入: 12 输出: [[2, 6],[2, 2, 3],[3, 4]]

// 您可以假设n总是正数 因数应大于1且小于n

public class FactorCombnations {
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
