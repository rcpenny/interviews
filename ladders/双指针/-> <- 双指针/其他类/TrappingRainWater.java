// 给出 n 个非负整数，代表一张X轴上每个区域宽度为 1 的海拔图, 计算这个海拔图最多能接住多少（面积）雨水

// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
// 输出: 6
// Challenge
// O(n) 时间, O(1) 空间

// 这个解法 O(n)空间
public class TrappingRainWater {
	public int trapRainWater(int[] heights) {
		if (heights == null || heights.length <= 2) return 0;

		int rain = 0;

		int[] maxFromLeft = new int[heights.length];  // 向左看最高的高度
		int[] maxFromRight = new int[heights.length]; // 向右看最高的高度

		int cur_max_left = 0;
		for (int i = 0; i < heights.length; i++) {
			cur_max_left = Math.max(cur_max_left, heights[i]);
			maxFromLeft[i] = cur_max_left;
		}

		int cur_max_right = 0;
		for (int i = heights.length - 1; i >= 0; i--) {
			cur_max_right = Math.max(cur_max_right, heights[i]);
			maxFromRight[i] = cur_max_right;
		}

		for (int i = 0; i < heights.length; i++) {
			int maxLeft = maxFromLeft[i];
			int maxRight = maxFromRight[i];
			int cur_height = heights[i];

			if (cur_height < maxLeft && cur_height > maxRight) {
				rain += Math.min(maxLeft, maxRight) - height;
			}
		}

		return rain;
	}

	// O(N) O(1)
	public int trap(int[] height) {
    int water = 0;

    if (height == null || height.length == 0) {
      return water;
    }
    
    int left = 0;
    int right = height.length - 1;

    int leftMax = 0;
    int rightMax = 0;

    while (left < right) {
      // 先比较两个pointer left和right
      if (height[left] < height[right]) {
        if (leftMax > height[left]) {
          water += leftMax - height[left];
        } else {
          leftMax = height[left];
        }
        left++;
      } 
      
      else {
        if (rightMax > height[right]) {
          water += rightMax - height[right];
        } else {
          rightMax = height[right];
        }
        right--;
      }
    }

    return water;
  }
}
