// 给定n个整数的数组nums，其中n > 1，返回一个数组输出
// 使得output [i]等于nums的所有除了nums [i]的元素的乘积
// lint1310

public class ProductOfArrayExceptSelf {

	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;

		int[] from_left = new int[n];
		int[] from_right = new int[n];

		from_left[0] = nums[0];
		for (int i = 1; i < n; i++)
			from_left[i] = nums[i] * from_left[i - 1];

		from_right[n - 1] = nums[n - 1];
		for (int i = n - 2; i >= 0; i--)
			from_right[i] = nums[i] * from_right[i + 1];
			
		int[] result = new int[n];
		result[0] = from_right[1];
		result[n - 1] = from_left[n - 2];

		for (int i = 1; i < n - 1; i++)
			result[i] = from_left[i - 1] * from_right[i + 1];

		return result;
	}
}
