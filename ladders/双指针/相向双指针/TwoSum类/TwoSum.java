import java.util.HashMap;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are zero-based.
 * 
 * You may assume that each input would have exactly one solution
 */

public class TwoSum {
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		HashMap<Integer, Integer> numToIndex = new HashMap<>();

		for (int i = 0; i < numbers.length; i++) {
			int comp = target - numbers[i];
			if (numToIndex.containsKey(comp)) {
				result[0] = numToIndex.get(comp);
				result[1] = i;
				break;
			}
			// 先put 会出错，比如[1, 0, -1] target是0
			numToIndex.put(numbers[i], i);
		}
		return result;
	}
}
