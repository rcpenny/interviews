import java.util.Stack;

// 给你一个二维矩阵，权值为False和True，找到一个最大的矩形，使得里面的值全部为True，输出它的面积
// 输入:
// [
//   [1, 1, 0, 0, 1],
//   [0, 1, 0, 0, 1],
//   [0, 0, 1, 1, 1],
//   [0, 0, 1, 1, 1],
//   [0, 0, 0, 0, 1]
// ]
// 输出: 6

// 以矩阵每一行为直方图的底部，通过1确定每个柱形的高度

public class MaximalRectangle {
	public int max = 0;

	public int maximalRectangle(boolean[][] matrix) {
		if (matrix == null || matrix.length == 0) return 0;

		int[][] height = new int[matrix.length][matrix[0].length];

		// generate height[][] O(m * n)
		for (int i = 0; i < matrix[0].length; i++) height[0][i] = matrix[0][i] ? 1 : 0;
		for (int i = 0; i < matrix[0].length; i++) { // column is i
			for (int j = 1; j < matrix.length; j++) if (matrix[j][i]) height[j][i] = 1 + height[j - 1][i];				
		}

		// 以每行作为直方图底部
		for (int i = 0; i < height.length; i++) largest(height[i]);

		return max;
	}

	private void largest(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i <= heights.length; i++) {
			int current_height = i == heights.length ? -1 : heights[i];

			while (!stack.empty() && current_height <= heights[stack.peek()]) {
				int tmp_index, tmp_height, tmp_width;
				tmp_index = stack.pop();
				tmp_height = heights[tmp_index];
				if (stack.empty()) tmp_width = i;
				else tmp_width = i - stack.peek() - 1;

				max = Math.max(max, tmp_width * tmp_height);
			}
			stack.push(i);
		}
	}
}
