import java.util.Stack;

// Design a max stack that supports push, pop, top, peekMax and popMax.

// push(x) -- Push element x onto stack.
// pop() -- Remove the element on top of stack and return it.
// top() -- Get the element on the top.
// peekMax() -- Retrieve the maximum element in the stack.
// popMax() -- Retrieve the maximum element in the stack, and remove it. 
// If you find more than one maximum elements, only remove the top-most one.

// Note:
// -1e7 <= x <= 1e7
// Number of operations won't exceed 10000.
// The last four operations won't be called when stack is empty.

// 更优解用treemap
// 说了下用两个stack存数据的思路，不满意。换了treemap的方法，多的时间问了下如果是要线程安全你该怎么改。

class MaxStack {
	// 解1 双栈
	private Stack<Integer> realStack;
	private Stack<Integer> maxStack;
	
	public MaxStack() {
		this.realStack = new Stack<>();
		this.maxStack = new Stack<>();
	}
	
	public void push(int x) {
		realStack.push(x);
		if (maxStack.isEmpty()) 
			maxStack.push(x);
		else 
			maxStack.push(Math.max(maxStack.peek(), x));
	}

	public int pop() {
		maxStack.pop();
		return realStack.pop();
	}
	
	public int top() {
		return realStack.peek();
	}
	
	public int peekMax() {
		return maxStack.peek();
	}
	
	public int popMax() {
		Stack<Integer> tmp = new Stack<>();
		int cur_max = peekMax();

		while (top() != cur_max) {
			tmp.push(realStack.pop());
			maxStack.pop();
		}

		realStack.pop();
		maxStack.pop();

		while (!tmp.isEmpty()) {
			push(tmp.pop());
		}

		return cur_max;
	}

	// 解2 treemap
}
