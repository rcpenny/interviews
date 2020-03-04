import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// 给定由非负整数组成的非空数组，数组的度定义为出现频率最高的元素。

// 找出最短的连续子数组，并使得它和原数组有相同的度。返回该连续子数组的长度。
// nums.length的范围在1到50,000之间。
// nums[i]是范围为0到49,999的整数。

// 输入: [1, 2, 2, 3, 1]
// 输出: 2
// 解释: 
// 输入数组的度是2，1和2都出现了两次。
// 具有相同度的子串包括：
// [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
// 其中长度最短为2。所以返回2。

class ArrayDegree {
	public int findShortestSubArray(int[] nums) {
		if (nums.length == 1) return 1;

		int max = 0;
		Map<Integer, Integer> numToFreq = new HashMap<>();

		for (int num : nums) {
			if (!numToFreq.containsKey(num)) numToFreq.put(num, 1);
			else numToFreq.put(num, numToFreq.get(num) + 1);
			max = Math.max(max, numToFreq.get(num));
		}
        
    if (max == 1) return 1;

		Map<Integer, List<Integer>> numToIndex = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int tmp = nums[i];
			if (numToFreq.get(nums[i]) != max) continue;

			if (!numToIndex.containsKey(nums[i])) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				list.add(i);
				numToIndex.put(tmp, list);
			} else {
				numToIndex.get(tmp).set(1, i);
			}
		}

    int result = nums.length;
    for (int num : numToIndex.keySet()) {
      int left = numToIndex.get(num).get(0);
      int right = numToIndex.get(num).get(1);
      result = Math.min(result, right - left + 1);
    }
    
		return result;
	}
}
