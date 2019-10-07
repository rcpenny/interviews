// 给定一个每日temperatures的列表，产生一个列表，对于输入的每天，告诉我们你要等多少天才能够等到一个更高的温度。
// 如果没有可能的未来日期，输出0作为替代
// 比如，给定列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]。

// lc739

public class DailyTemperatures {
	public int[] dailyTemperatures(int[] temperatures) {
		if (temperatures == null || temperatures.length == 0) return new int[0];

		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
				result[stack.peek()] = i - stack.peek();
				stack.pop();
			}
			stack.push(i);
		}

		return result;
	}
}
