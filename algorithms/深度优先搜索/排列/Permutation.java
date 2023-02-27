import java.util.ArrayList;
import java.util.List;

// Given a list of numbers, return all possible permutations.
// You can assume that there is no duplicate numbers in the list.
// 画树想象排列

public class Permutation {
  public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) return results;

		boolean[] visited = new boolean[nums.length];		
		dfs(nums, visited, new ArrayList<Integer>(), results);

		return results;
	}

	// 定义： 元数据nums, 控制visited, 状态list, 结果results
	private void dfs(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> results) {
		// 出口：排列长度等于元数据长度
	  if (list.size() == nums.length) {
			results.add(new ArrayList<>(list));
			return;
		}

		// 拆解：从头开始扫，哪些数字可以选
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) continue; // 进入递归的条件：这个位置的数字没被选过

			visited[i] = true;
			list.add(nums[i]);
			dfs(nums, visited, list, results);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}
}
