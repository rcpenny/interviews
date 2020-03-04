// 给定n个整数的数组nums，其中n > 1，返回一个数组输出
// 使得output [i]等于nums的所有除了nums [i]的元素的乘积
// leet238

public class ProductOfArrayExceptSelf {
	// O(3n)
	public int[] productExceptSelf(int[] nums) {
    int n = nums.length;

    int[] result = new int[n];

    int[] productFromLeft = new int[n];
    int[] productFromRight = new int[n];

    productFromLeft[0] = nums[0];
    for (int i = 1; i < n - 1; i++) {
      productFromLeft[i] = productFromLeft[i - 1] * nums[i];
    }

    productFromRight[n - 1] = nums[n - 1];
    for (int i = n - 2; i > 0; i--) {
      productFromRight[i] = productFromRight[i + 1] * nums[i];
    }

    result[0] = productFromRight[1];
    result[n - 1] = productFromLeft[n - 2];

    for (int i = 1; i < n - 1; i++) {
      result[i] = productFromLeft[i - 1] * productFromRight[i + 1];
    }

    return result;
	}
	
	// O(n)
	public int[] productExceptSelf2(int[] nums) {
		int n = nums.length;

		int leftProduct = 1;
		int rightProduct = 1;

		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = 1;
		}

		for (int i = 0; i < n; i++) {
			result[i] *= leftProduct;
			leftProduct *= nums[i];

			result[n - 1 - i] *= rightProduct;
			rightProduct *= nums[n - i - 1];
		}

		return result;
	}
}
