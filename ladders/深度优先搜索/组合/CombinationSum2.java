import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数组 candiates 和一个整数 target. 找到 candiates 中所有的数字之和为 target 的组合.
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

	// 定义：元数据candidates target  控制start  状态comb  结果results
	private void dfs(int[] candiates, int target, int start, List<Integer> comb, List<List<Integer>> results) {
		// 出口：remain target <= 0
		if (target == 0) results.add(new ArrayList<Integer>(comb));
		if (target <= 0) return;

		// 拆解：这层树可以选的数字，一个个选
		for (int i = start; i < candiates.length; i++) {
			// 进入递归的条件：去重，控制在这个层级（树）
			if (i != start && candiates[i] == candiates[i - 1]) continue;
			comb.add(candiates[i]);
			dfs(candiates, target - candiates[i], i + 1, comb, results); // i + 1，此数字不能被重复选
			comb.remove(comb.size() - 1);
		}
	}
}
