import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个候选数字的集合 candidates 和一个目标值 target.
 * 找到 candidates 中所有的和为 target 的组合.
 * 在同一个组合中, candidates 中的某个数字不限次数地出现.
 * 
 * 所有数值 (包括 target ) 都是正整数.
 * 返回的每一个组合内的数字必须是非降序的.
 * 返回的所有组合之间可以是任意顺序.
 * 解集不能包含重复的组合.
 * 
 * 输入: candidates = [2, 3, 6, 7], target = 7
 * 输出: [[7], [2, 2, 3]]
 */

public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();
		if (candidates == null || candidates.length == 0) return results;

		Arrays.sort(candidates);
		dfs(candidates, target, 0, new ArrayList<>(), results);

		return results;
	}

	// 定义：元数据candidates,target  控制start  状态comb  结果results
	private void dfs(int[] candidates, int target, int start, List<Integer> comb, List<List<Integer>> results) {
		// 出口: remain target <= 0
		if (target == 0) results.add(new ArrayList<Integer>(comb));
		if (target <= 0) return;

		// 拆解：这层树可以选的数字，一个个选
		for (int i = start; i < candidates.length; i++) {
			// 进入递归的条件：去重，控制在这个层级（树）
			if (i > start && candidates[i] == candidates[i - 1]) continue;

			comb.add(candidates[i]);
			dfs(candidates, target - candidates[i], i, comb, results); // i 可以被重复选
			comb.remove(comb.size() - 1);
		}
	}
}
