import java.util.HashMap;

// 经典题

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
			// if前先put会出错
			numToIndex.put(numbers[i], i);
		}
		return result;
	}
}
