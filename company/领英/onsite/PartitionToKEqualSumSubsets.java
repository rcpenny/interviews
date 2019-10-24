// 给定一个整数数组nums和一个正整数k，是否有可能能够把这个数组分成k个非空的子集，满足子集的sum全部都相等
// 输入: nums = [4, 3, 2, 3, 5, 2, 1] 和 k = 4
// 输出: True
// 解释: 一个可能的划分 (5), (1, 4), (2, 3), (2, 3)，拥有相等的权重

// 这是一个组合问题
// 给一个数组，求有多少子集合等于一个给定值。用递归做完了，然后说了时间空间开销。之后问怎么优化空间，我有点懵逼，感觉已经最优了。
// 后来聊了一下才知道，面试官是想在系统盏里面少存局部变量
// 所以，递归之前把不需要改变的变量全部用全局变量存，然后再递归，这样递归就少存一些变量在系统盏里面。


// 不过有一些加速方法可以采用，这里列举其中一些：
// k个子集从前到后递归，如果当前的子集和与前一个子集和相同，那么这个子集就不用试了，
// 因为把n放到这个子集和放到前一个子集没有差别。我们只关心能否搜索到，并不关心具体的分配方案。
// 先把nums排序，并优先先放入最大的元素，这样能减少许多搜索路径。
// 一旦找到nums[i] > target，那么就直接返回False。因为如果某一个元素，都超过了target，那么就一定不合题。

public class PartitionToKEqualSumSubsets {
  public boolean partitiontoEqualSumSubsets(int[] nums, int k) {
		int sum = Arrays.stream(nums).sum();
		if (sum % k != 0) return false;

		boolean[] visited = new boolean[nums.length];

		return find(nums, visited, 0, k, 0, sum / k);
	}

	private boolean find(int[] nums, boolean[] visited, int start, int k, int sum, int target) {
		if (k == 1) return true;

		if (sum == target)
			return find(nums, visited, 0, k - 1, 0, target);
		
		for (int i = start; i < nums.length; i++) {
			if (visited[i] || sum + nums[i] > target) continue;

			visited[i] = true;

			if (find(nums, visited, i + 1, k, sum + nums[i], target))
				return true;

			visited[i] = false;
		}

		return false;
	}
}
