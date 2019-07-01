import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k. 
 * Return all possible combinations of k numbers out of 1, 2, ... , n.
 * 
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * 
 *    	         k = 0   						  []
 * 	 k = 1				[1]    				  [2]        [3]    [4]
 *   k = 2 [1,2] [1,3] [1,4]	[2,3] [2,4]   [3,4]
 */
public class Combinations {
  public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> results = new ArrayList<>();
		dfs(n, k, 1, new ArrayList<Integer>(), results);
		return results;
	}

	private void dfs(int n, int k, int startIndex, 
		List<Integer> comb, List<List<Integer>> results) {
		if (comb.size() == k) {
			results.add(new ArrayList<Integer>(comb));
			return;
		}

		for (int i = startIndex; i <= n; i++) {
			comb.add(i);
			dfs(n, k, i + 1, comb, results);
			comb.remove(comb.size() - 1);
		}
	}
}
