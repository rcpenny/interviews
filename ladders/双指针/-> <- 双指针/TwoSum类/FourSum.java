import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 */
public class FourSum {
	public List<List<Integer>> fourSum(int[] numbers, int target) {
		List<List<Integer>> results = new ArrayList<>();
		Arrays.sort(numbers);

		for (int i = 0; i < numbers.length - 3; i++) {
			if (i != 0 && numbers[i] == numbers[i - 1]) continue;
			for (int j = i + 1; j < numbers.length - 2; j++) {
				if (j != i + 1 && numbers[j] == numbers[j - 1]) continue;

				int left = j + 1, right = numbers.length - 1;
				while (left < right) {
					int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
					if (sum < target) {
						left++;
					} else if (sum > target) {
						right--;
					} else if (sum == target) {
						List<Integer> quadruplets = Arrays.asList(numbers[i], numbers[j], numbers[left], numbers[right]);
						results.add(quadruplets);
						// left < right 这个条件不要miss!!
						left++;
						right--;
						while (left < right && numbers[left] == numbers[left - 1]) {
							left++;
						}
						while (left < right && numbers[right] == numbers[right + 1]) {
							right--;
						}
					}
				}
			}
		}
		return results;
	}
}
