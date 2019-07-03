import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of numbers, return all possible permutations.
 * You can assume that there is no duplicate numbers in the list.
 * 画树想象排列
 */
public class Permutation {
  public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) return results;

		boolean[] visisted = new boolean[nums.length];		
		dfs(nums, visisted, new ArrayList<Integer>(), results);

		return results;
	}

	//               原始数据      对原始数据状态的控制     当前DFS位置的状态
	private void dfs(int[] nums, boolean[] visisted, List<Integer> list, 
		List<List<Integer>> results) {
		// recursion exit 当前位置为理想状态
	  if (list.size() == nums.length) {
			results.add(new ArrayList<>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			// 进入下个递归前的额外操作：去重？去掉特殊条件？
			// 判断这个这个位置的数字是否被选过
			if (visisted[i]) continue;

			visisted[i] = true;
			list.add(nums[i]);
			dfs(nums, visisted, list, results);
			visisted[i] = false;
			list.remove(list.size() - 1);
		}
	}
}
