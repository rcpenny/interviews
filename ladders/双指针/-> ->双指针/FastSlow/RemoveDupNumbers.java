import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, remove the duplicate numbers in it.
 * Return the total number of the unique numbers.
 * 
 * Input:
 * nums = [1,3,1,4,4,2]
 * Output:
 * [1,3,4,2,?,?]
 * 4
 * 
 * 看图写码
 */

public class RemoveDupNumbers {

  public int deduplication(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		Set<Integer> numbers = new HashSet<>();

		int fast = 0,slow = 0;

		while (fast < nums.length) {
		  int cur_num = nums[fast];
			if (!numbers.contains(cur_num)) {
				nums[slow] = cur_num;
				numbers.add(cur_num);
				slow++;
			}	
			fast++;
		}
		return numbers.size();
  }
}
