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
    List<List<Integer>> results = new ArrayList<>();
    if (n < 2) return results;

		int[] factors = allFactorsInAscendOrder(n);
		factorize(factors, 0, n, new ArrayList<>(), results);

    return results;
  }

  private void factorize(int[] factors, int startIndex, int n, 
    List<Integer> list, List<List<Integer>> results) {
		if (n == 1) results.add(new ArrayList<>(list));
		if (n <= 1) return;

    for (int i = startIndex; i < factors.length; i++) {
      // 12 / 2 / 2 / 2 / 2 = 1 :)
      if (n % factors[i] != 0) continue;
      list.add(factors[i]);
      factorize(factors, i,  n / factors[i], list, results); 
      list.remove(list.size() - 1);
    }
  }

  private int[] allFactorsInAscendOrder(int n) {
    List<Integer> list = new ArrayList<>();
    for (int i = 2; i <= n / 2; i++) if (n % i == 0) list.add(i);
    Collections.sort(list);
    int[] factors = new int[list.size()];
    for (int i = 0; i < list.size(); i++) factors[i] = list.get(i);
    return factors;
  }
}
