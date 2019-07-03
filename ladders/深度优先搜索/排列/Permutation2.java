import java.awt.List;
import java.util.ArrayList;

/**
 * Given a list of numbers with duplicate number in it. 
 * Find all unique permutations.
 */
public class Permutation2 {
  public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) return results;

		boolean[] visisted = new boolean[nums.length];
		Arrays.sort(nums);
		dfs(nums, visisted, new ArrayList<Integer>(), results);

		return results;
	}

	private void dfs(int[] nums, boolean[] visisted, List<Integer> permute, 
		List<List<Integer>> results) {
		if (permute.size() == nums.length) {
			results.add(new ArrayList<>(permute));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			// 进入下个递归前的额外操作：去重？去掉特殊条件？
			// 判断这个这个位置的数字是否被选过
			if (visisted[i]) continue;
			// 该层是否以这个数字开头过了
			if (i != 0 && nums[i] == nums[i - 1] && !visisted[i - 1]) continue;

			permute.add(nums[i]);
			visisted[i] = true;
			dfs(nums, visisted, permute, results);
			permute.remove(permute.size() - 1);
			visisted[i] = false;
		}
	}

	// [1, 1, 2, 3, 3, 4]
	// 为什么是!visisted[i - 1]呢？
	// 比如说第一个1 开头找的所有的Path结束了。 一个tree, 结束， visisited[0]变成 false
	// 这个时候第二个1就不用再考虑了。
	// 类比所有情况
}
