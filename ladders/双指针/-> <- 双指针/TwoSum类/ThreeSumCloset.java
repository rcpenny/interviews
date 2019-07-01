/**
 * Given an array S of n integers, find three integers in S such that the sum is 
 * closest to a given number, target. Return the sum of the three integers.
 * 
 * Input:[2,7,11,15],3
 * Output:20
 * Explanation:
 * 2+7+11=20
 * 
 * You may assume that each input would have exactly one solution.
 */
public class ThreeSumCloset {
	private int minDiff = Integer.MAX_VALUE;
	private int result = 0;

  public int threeSumClosest(int[] numbers, int target) {
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length - 2; i++) {
			if (i != 0 && numbers[i] == numbers[i - 1]) continue;
			twoSumCloset(numbers, i, target);
		}
		return result;
	}

	private void twoSumCloset(int[] numbers, int start, int target) {
		int first = numbers[start];
		int left = start + 1, right = numbers.length - 1;
		while (left < right) {
			int sum = first + numbers[left] + numbers[right];
			if (target == sum) {
				result = sum;
				left++;
				right--;
			} else if (sum > target) {
				if (sum - target < minDiff) {
					minDiff = sum - target;
					result = sum;
				}
				right--;
			} else {
				if (target - sum < minDiff) {
					minDiff = target - sum;
					result = sum;
				}
				left++;
			}
		}
	}
}
