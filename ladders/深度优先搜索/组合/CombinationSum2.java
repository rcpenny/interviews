import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数组 candiates 和一个整数 target. 找到 candiates 中所有的数字之和为 target 的组合.
 * can
 * 在同一个组合中, candiates 中的每一个数字仅能被使用一次.
 * 所有数值 (包括 target ) 都是正整数.
 * 返回的每一个组合内的数字必须是非降序的.
 * 返回的所有组合之间可以是任意顺序.
 * 解集不能包含重复的组合.
 */
public class CombinationSum2 {
  public List<List<Integer>> combinationSum2(int[] candiates, int target) {
		List<List<Integer>> results = new ArrayList<>();
		if (candiates == null) return results;
		Arrays.sort(candiates);
		dfs(candiates, target, 0, new ArrayList<Integer>(), results);
		return results;
	}

	private void dfs(int[] candiates, int target, int start, List<Integer> comb, 
		List<List<Integer>> results) {
		if (target < 0) return;
		if (target == 0) {
			results.add(new ArrayList<Integer>(comb));
			return;
		}

		for (int i = start; i < candiates.length; i++) {
			if (i != start && candiates[i] == candiates[i - 1]) continue;
			comb.add(candiates[i]);
			dfs(candiates, target - candiates[i], i + 1, comb, results);
			comb.remove(comb.size() - 1);
		}
	}
}
