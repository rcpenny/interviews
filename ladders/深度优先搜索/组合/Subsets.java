import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个含不同整数的集合，返回其所有的子集。
 * 子集中的元素排列必须是非降序的，解集必须不包含重复的子集。
 */

public class Subsets {

  public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();	
		if (nums == null) return results;
		Arrays.sort(nums);

		dfs(nums, 0, new ArrayList<Integer>(), results);
		return results;
	}

	private void dfs(int[] nums, int start, List<Integer> subset, 
		List<List<Integer>> results) {
			results.add(new ArrayList<Integer>(subset));
			for (int i = start; i < nums.length; i++) {
				subset.add(nums[i]);
				dfs(nums, i + 1, subset, results);
				subset.remove(subset.size() - 1);
			}
	}
}
