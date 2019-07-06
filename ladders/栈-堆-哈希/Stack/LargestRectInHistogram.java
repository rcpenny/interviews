import java.util.Stack;

// 给出的n个非负整数表示每个直方图的高度，每个直方图的宽均为1，在直方图中找到最大的矩形面积。

// 输入：[2,1,5,6,2,3]
// 输出：10
// 解释：
// 第三个和第四个直方图截取矩形面积为2*5=10

// stack([1,5,6]).push(2) => stack([1,2]  
// pop掉了5与6，找到了比5小的第一个高度：1

public class LargestRectInHistogram {
	public int max = 0;

  public int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) return 0;

		Stack<Integer> stack = new Stack<>(); 
		for (int i = 0; i <= height.length; i++) {
			maintainMonoStack(height, i, stack);
		}

		return max;
	}

	private void maintainMonoStack(int[] height, int current_index, Stack<Integer> stack) {
		// 多出一位 -1，保证最后个直方图3也能向左找
		int current_height = (current_index == height.length) ? -1 : height[current_index];

		// 栈非空 且 当前高度 <= 栈顶index高度时，需要维护单调栈
		while (!stack.empty() && current_height <= height[stack.peek()]) {
			// 维护过程中（pop掉比current height大的高度时，然后当前面积打擂台）
			// 计算的是tmp_index向左的面积
			int tmp_index, tmp_height, tmp_width;

			tmp_index = stack.pop();
			tmp_height = height[tmp_index];

			if (stack.empty()) tmp_width = current_index;
			else tmp_width = current_index - stack.peek() - 1;

			max = Math.max(max, tmp_height * tmp_width);
		}

		stack.push(current_index);
	}
}
