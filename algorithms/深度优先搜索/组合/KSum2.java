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
		find(nums, k, target, 0, new ArrayList<>(), results);

		return results;
	}

	// 定义：元数据nums, k, target  控制start  状态list   结果results
	private void find(int[] nums,int k, int target, int start, List<Integer> list, List<List<Integer>> results) {
		// 出口：target <= 0, k越界
		if (target == 0) results.add(new ArrayList<>(list));
		if (k <= 0 || target <= 0) return;

		// 拆解：一个个选就是了
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			find(nums, k - 1, target - nums[i], i + 1, list, results);
			list.remove(list.size() - 1);
		}
	}
}
