import java.util.Stack;

/**
 * Given an expression s contains numbers, letters and brackets. 
 * Number represents the number of repetitions inside the brackets(can be a string or another expression)．
 * Please expand expression to be a string.
 */
// Input: S = 3[2[ad]3[pf]]xyz
// Output: "adadpfpfpfadadpfpfpfadadpfpfpfxyz"

public class DecodeString {
  public String expressionExpand(String s) {
		// 用object厉害了，或者用两个stack，一个存数字，一个存字符串
		Stack<Object> stack = new Stack<>();
		int number = 0;

		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				number = number * 10 + (c - '0');
			} else if (c == '[') {
				stack.push(Integer.valueOf(number));
				number = 0;
			} else if (c == ']') {
				String newStr = popStack(stack);
				Integer count = (Integer) stack.pop();
				// 这步是精髓，把生成的新string push回stack，这样嵌套[就被处理了
				for (int i = 0; i < count; i++) {
					stack.push(newStr);
				}
			} else {
				stack.push(String.valueOf(c));
			}
		}
		return popStack(stack);
	}

	private String popStack(Stack<Object> stack) {
		Stack<String> buffer = new Stack<>();
		// 这里不处理integer，在调这个method之后再pop出数字
		while (!stack.isEmpty() && (stack.peek() instanceof String)) {
			buffer.push((String) stack.pop());
		}

		StringBuilder sb = new StringBuilder();
		while (!buffer.isEmpty()) sb.append(buffer.pop());
		return sb.toString();
	}
}
