import java.util.Stack;

// 给出的n个非负整数表示每个直方图的高度，每个直方图的宽均为1，在直方图中找到最大的矩形面积。

// Example
// 样例 1:

// 输入：[2,1,5,6,2,3]
// 输出：10
// 解释：
// 第三个和第四个直方图截取矩形面积为2*5=10

public class LargestRectInHistogram {
  public int largestRectangleArea(int[] height) {
		int max = 0;
		if (height == null || height.length == 0) return max;

		Stack<Integer> stack = new Stack<>(); // 维护此栈单调递增
		for (int i = 0; i <= height.length; i++) {
			int current = (i == height.length) ? -1 : height[i];
			while (!stack.empty() && current <= height[stack.peek()]) {
				int h = height[stack.pop()];
				int w = stack.empty() ? i : i - stack.peek() - 1;
				max = Math.max(max, h * w);
			}
			stack.push(i);
		}

		return max;
	}
}

// 分析：
// • 最大矩形一定是某一个柱形往左往右直到不能前进，形成的矩形
// • 需要知道一个数字往左和往右第一个小于这个数字的位置
// • 单调递增栈
// – 压栈时弹出大于等于自己的值
// – 最后停下来时碰到的栈顶就是左边第一个比自己小的值
// – 一个数X被新来的值R弹出栈顶，那么R就是X右边第一个小于等于X的值
// • 如果有相同的数，那么最靠右的bar会求得最大面积
// • 最后插入-1
// • 时间复杂度O(N)
