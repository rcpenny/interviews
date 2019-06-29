import java.util.ArrayList;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S 
 * such that a + b + c = 0? Find all unique triplets in the array 
 * which gives the sum of zero.
 * 
 * Input:[-1,0,1,2,-1,-4]
 * Output:	[[-1, 0, 1],[-1, -1, 2]]
 * 
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 */

public class ThreeSum {
  public List<List<Integer>> threeSum(int[] numbers) {
		List<List<Integer>> results = new ArrayList<>();
		if (numbers == null || numbers.length < 3) return results;
		Arrays.sort(numbers);

		// 循环起点需要skip dupes
		for (i = 0; i < numbers.length - 2; i++) {
			if (i != 0 && numbers[i] == numbers[i - 1]) continue;
			twoSum(numbers, i, results);
		}
		return results;
	}

	private void twoSum(int[] numbers, int start, List<List<Integer>> results) {
		int target = 0 - numbers[start];
		int left = start + 1, right = numbers.length - 1;

		while (left < right) {
			int sum = numbers[left] + numbers[right];
			if (sum == target) {
				List<Integer> triple = new ArrayList<>();
				triple.add(numbers[start]);
				triple.add(numbers[left]);
				triple.add(numbers[right]);
				results.add(triple);
				left++;
				right--;
				// 精髓： 在找到一个triple之后，再skip duplicates
				while (left < right && numbers[left] == numbers[left - 1]) left++;
				while (left < right && numbers[right] == numbers[right + 1]) right--;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
	}

}
