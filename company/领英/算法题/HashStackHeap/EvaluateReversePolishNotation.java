import java.util.Stack;

// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.

// Input: ["4", "13", "5", "/", "+"]  Output: 6
// Explanation: ["4", "13", "5", "/", "+"] -> 4 + 13 / 5 -> 6

public class EvaluateReversePolishNotation {
  public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length < 3) return Integer.MIN_VALUE;
		String operators = "+-*/";

		Stack<Integer> stack = new Stack<>();

		for (String token : tokens) {
			if (!operators.contains(token)) {
				stack.push(Integer.valueOf(token));
				continue;
			}
			Integer second = stack.pop();
			Integer first = stack.pop();
			Integer next = caculate(token, first, second);
			stack.push(next);
		}

		return stack.pop();
	}

	private Integer caculate(String operator, Integer first, Integer second) {
		if (operator.equals("+")) return first + second;
		if (operator.equals("-")) return first - second;
		if (operator.equals("*")) return first * second;
		
		if (second != 0) return first / second;
		else return Integer.MAX_VALUE;
	}
}
