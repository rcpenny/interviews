// leet341

// 输入: [[2,1],2,[5,6]]
// 输出: [2,1,2,5,6]

public class NestedIterator implements Iterator<Integer> {
  private Stack<NestedInteger> stack;

  private NestedIterator(List<NestedInteger> nestedList) {
    this.stack = new Stack<>();
    pushNestedListToStack(nestedList);
  }

  @Override
  public Integer next() {
    if (!hasNext()) return null; // hasnext 强制处理结构
    return stack.pop().getInteger();
  }

	/** 若stack peek is NestedList, push it */
  @Override
  public boolean hasNext() {
    while (!stack.isEmpty() && !stack.peek().isInteger()) {
      pushNestedListToStack(stack.pop().getList());
    }
    return !stack.isEmpty();
  }

  /** 把这一层的nested integer push进stack, 倒装， 画图想一下 */
  private void pushNestedListToStack(List<NestedInteger> nestedList) {
		Stack<NestedInteger> buffer = new Stack<>();
		
		for (NestedInteger ni : nestedList) {
			buffer.push(ni);
		}

		while (!buffer.isEmpty()) {
			this.stack.push(buffer.pop());
		}
	}
}

class FlattenNestedterator {}
