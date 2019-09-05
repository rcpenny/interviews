import java.util.Iterator;

// 341
// 给你一个嵌套的列表，实现一个迭代器将其摊平
// 一个列表的每个元素可能是整数或者一个列表

// 输入: list = [[1,1],2,[1,1]]
// 输出: [1,1,2,1,1]

// Input: [1,[4,[6]]]
// Output: [1,4,6]

public class NestedIterator implements Iterator<Integer> {
	private Stack<NestedInteger> stack;

	public NestedIterator(List<NestedInteger> nestedList) {
		this.stack = new Stack<>();
		pushListToStack(nestedList);
	}

	@Override
	public Integer next() {
		if (!hasNext()) return null; // hasNext 会把stack的top变成integer
		return stack.pop().getInteger();
	}

	@Override
	public boolean hasNext() {
		while (!stack.isEmpty() && !stack.peek().isInteger())
			pushListToStack(stack.pop().getList());
		
		return !stack.isEmpty();
	}
	
	// 把这一层的NestedInteger push进去,通过一个tmp stack完成方向反转
	private void pushListToStack(List<NestedInteger> nestedList) {
		Stack<NestedInteger> tmp = new Stack<>();
		for (NestedInteger ni : nestedList)
			tmp.push(ni);

		while (!tmp.isEmpty())
			stack.push(tmp.pop());
	}

	@Override public void remove() {}
}
