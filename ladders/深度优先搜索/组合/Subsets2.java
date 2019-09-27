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

	// 定义：元数据nums, 控制start, 状态subset, 结果results
	private void dfs(int[] nums, int start, List<Integer> subset, List<List<Integer>> results) {
		// 出口：不用，加入递归树的每一个节点
		results.add(new ArrayList<>(subset));

		// 拆解：每个for就是递归树的一层，从start开始查
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1]) continue;
			subset.add(nums[i]);
			dfs(nums, i + 1, subset, results);
			subset.remove(subset.size() - 1);
		}
	}
}
