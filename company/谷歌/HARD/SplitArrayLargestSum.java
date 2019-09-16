// Given an array which consists of non-negative integers and an integer m, 
// you can split the array into m non-empty continuous subarrays.
//  Write an algorithm to minimize the largest sum among these m subarrays.
// Input:
// nums = [7,2,5,10,8]
// m = 2

// Output:
// 18

// Explanation:
// There are four ways to split nums into two subarrays.
// The best way is to split it into [7,2,5] and [10,8],
// where the largest sum among the two subarrays is only 18.

//leet410

// 这种题最好用long
public class SplitArrayLargestSum {
	public int splitArray(int[] nums, int m) {
		long start = 0;
		long end = 0;

		for (int num : nums) {
			start = Math.max(start, num);
			end += num;
		}

		while (start + 1 < end) {
			long mid = start + (end - start) / 2;
			if (canSplit(nums, m, mid))
				start = mid;
			else
				end = mid;
		}

		if (canSplit(nums, m, end)) return (int) end;
		return (int) start;
	}

	private boolean canSplit(int[] nums, int m, long sum) {
		int cur_sum = 0;
		int i = 0, n = nums.length;
		while (i < n) {
			int tmp =  nums[i];
			if (tmp + cur_sum < sum) {
				cur_sum += tmp;
				i++;
			} else {
				cur_sum = 0;
				m--;
			}
		}
		return m <= 0;
	}
}

// 二分答案
