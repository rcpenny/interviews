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
		boolean[] visisted = new boolean[nums.length];	
	
		dfs(nums, visisted, new ArrayList<Integer>(), results);
		return results;
	}

	private void dfs(int[] nums, boolean[] visisted, List<Integer> list, 
		List<List<Integer>> results) {
	  if (list.size() == nums.length) {
			results.add(new ArrayList<>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visisted[i]) continue;
			visisted[i] = true;
			list.add(nums[i]);
			dfs(nums, visisted, list, results);
			visisted[i] = false;
			list.remove(list.size() - 1);
		}
	}
}
