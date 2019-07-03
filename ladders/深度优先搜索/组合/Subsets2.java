import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能具有重复数字的列表，返回其所有可能的子集。
		子集中的每个元素都是非降序的
		两个子集间的顺序是无关紧要的
		解集中不能包含重复子集
 */

public class Subsets2 {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) return results;
		
		Arrays.sort(nums);
		dfs(nums, 0, new ArrayList<Integer>(), results);

		return results;
	}

	private void dfs(int[] nums, int start, List<Integer> subset, List<List<Integer>> results) {
		// 没出口，每个节点都要搞搞事情
		results.add(new ArrayList<>(subset));

		for (int i = start; i < nums.length; i++) {
			// 这里是 i != start 去重的起点变了,去重发生在递归前那个层级
			if (i != start && nums[i] == nums[i - 1]) continue;
			subset.add(nums[i]);
			dfs(nums, i + 1, subset, results);
			subset.remove(subset.size() - 1);
		}
	}
}
