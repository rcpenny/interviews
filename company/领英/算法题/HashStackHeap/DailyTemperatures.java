// 给定一个每日temperatures的列表，产生一个列表，对于输入的每天，告诉我们你要等多少天才能够等到一个更高的温度。
// 如果没有可能的未来日期，输出0作为替代
// 比如，给定列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
// 你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]

// 对应 单调递减栈 stack
// index7  73 [7,
// index6  76 [6,
// index5  72 [6, 5, 
// index4  69 [6, 5, 4,
// index3  71 [6, 5, 3,
// index2  75 [6, 2,
// index1  74 [6, 2, 1
// index0  73 [6, 2, 1, 0

// leet739

// 该题是要找出右起第一个比当前数字大的元素，所以就对原始数组逆序遍历，使用单调递减栈
// 同时为了方便，我们在栈中放置索引，而不是温度值本身

public class DailyTemperatures {
	public int[] dailyTemperatures(int[] temperatures) {
		Stack<Integer> stack = new Stack<>(); // 栈中放置索引

		int n = temperatures.length;
		int[] result = new int[n];

		for (int i = n - 1; i >= 0; i--) {

			// 单调递减栈，栈顶的元素必须大于当前温度
			while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
				stack.pop();
			}

			// 维护之后的新栈顶（当前温度）与次栈顶的索引相减就是结果
			result[i] = stack.isEmpty() ? 0 : stack.peek() - i;

			stack.push(i);
		}

		return result;
	}
}
