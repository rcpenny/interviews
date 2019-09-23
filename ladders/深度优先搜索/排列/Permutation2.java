import java.awt.List;
import java.util.ArrayList;

// Given a list of numbers with duplicate number in it. 
// Find all unique permutations.

public class Permutation2 {

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) return results;

		// 排序是为了应对duplicates
		Arrays.sort(nums);
		boolean[] visited = new boolean[nums.length];
		dfs(nums, visited, new ArrayList<Integer>(), results);

		return results;
	}

	// 定义： 元数据nums, 控制visited, 状态list, 结果results
	private void dfs(int[] nums, boolean[] visited, List<Integer> permute, List<List<Integer>> results) {
		// 出口：排列长度等于元数据长度
		if (permute.size() == nums.length) {
			results.add(new ArrayList<>(permute));
			return;
		}

		// 拆解：从头开始扫，哪些数字可以选
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) continue; // 进入递归的条件：这个位置的数字没被选过 && 去重
			if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

			permute.add(nums[i]);
			visited[i] = true;
			dfs(nums, visited, permute, results);
			permute.remove(permute.size() - 1);
			visited[i] = false;
		}
	}

	// [1, 1, 1, 2, 3, 3, 4]
	// 为什么是!visited[i - 1]呢？
	// 比如说第一个1 开头找的所有的Path结束, 一个tree结束, visited[0]变成 false
	// 这个时候第二个1就不用再考虑了
}
