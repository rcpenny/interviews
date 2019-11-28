/**
 * https://www.lintcode.com/problem/min-stack/
 * 实现一个栈, 支持以下操作:
 * push(val) 将 val 压入栈
 * pop() 将栈顶元素弹出, 并返回这个弹出的元素
 * min() 返回栈中元素的最小值
 * 要求 O(1) 开销.
 */

public class MinStack {
	private Stack<Integer> stack;
	private Stack<Integer> minStack;

	public MinStack() {
		stack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}

	public void push(int number) {
		stack.push(number);
		if (minStack.empty()) {
			minStack.push(number);
		} else {
			int curMin = Math.min(number, minStack.peek());
			minStack.push(curMin);
		}
	}

	public int pop() {
		minStack.pop();
		return stack.pop();
	}

	public int min() {
		return minStack.peek();
	}
}
