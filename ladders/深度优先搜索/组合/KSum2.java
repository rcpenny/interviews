import java.awt.List;
import java.util.ArrayList;

/**
 * Given n unique postive integers, number k (1<=k<=n) and target.
 * Find all possible k integers where their sum is target.
 * 
 * Input: [1,2,3,4], k = 2, target = 5
 * Output:  [[1,4],[2,3]]
 */
public class KSum2 {
  public List<List<Integer>> kSumII(int[] nums, int k, int target) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null) return results;
		Arrays.sort(nums);

		find(nums, k, 0, target, new ArrayList<>(), results);
		return results;
	}

	private void find(int[] nums, int k, int start, int target, 
		List<Integer> list, List<List<Integer>> results) {
		if (k == 0) {
			if (target == 0) results.add(new ArrayList<>(list));
			return;
		}

		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			find(nums, k - 1, i + 1, target - nums[i], list, results);
			list.remove(list.size() - 1);
		}
	}
}
