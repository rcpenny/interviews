import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个候选数字的集合 candidates 和一个目标值 target.
 *  找到 candidates 中所有的和为 target 的组合.
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
		// 数据初始化 与 corner case
		List<List<Integer>> results = new ArrayList<>();
		if (candidates == null || candidates.length == 0) return results;

		// 操作
		Arrays.sort(candidates);
		dfs(candidates, target, 0, new ArrayList<>(), results);

		// 返回值
		return results;
	}

	private void dfs(int[] candidates, int target, int start,
		List<Integer> comb, List<List<Integer>> results) {
		// 以remain target(一个数字的大小)作为return exit condition
		if (target == 0) results.add(new ArrayList<Integer>(comb));
		if (target <= 0) return;

		for (int i = start; i < candidates.length; i++) {
			// 注意是i != start，去重，控制在这个层级（树），这个index的数可不可以选
			if (i != start && candidates[i] == candidates[i - 1]) continue;
			comb.add(candidates[i]);
			// i不变 进入下一层级时，这个的数可以被重复选
			dfs(candidates, target - candidates[i], i, comb, results);
			comb.remove(comb.size() - 1);
		}
	}
}
