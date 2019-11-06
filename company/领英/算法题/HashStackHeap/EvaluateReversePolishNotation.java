import java.util.Stack;

// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.

// Input: ["4", "13", "5", "/", "+"]  Output: 6
// Explanation: ["4", "13", "5", "/", "+"] -> 4 + 13 / 5 -> 6

// leet150
public class EvaluateReversePolishNotation {
  public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length < 3) {
			return Integer.MIN_VALUE;
		}

		String operators = "+-*/";

		Stack<Integer> stack = new Stack<>();

		for (String token : tokens) {
			if (!operators.contains(token)) {
				stack.push(Integer.valueOf(token));
				continue;
			}
			Integer secondOperand = stack.pop();
			Integer firstOperand = stack.pop();
			Integer result = caculate(token, firstOperand, secondOperand);
			stack.push(result);
		}

		return stack.pop();
	}

	private Integer caculate(String operator, Integer firstOperand, Integer secondOperand) {
		if (operator.equals("+")) return firstOperand + secondOperand;
		if (operator.equals("-")) return firstOperand - secondOperand;
		if (operator.equals("*")) return firstOperand * secondOperand;
		
		if (secondOperand != 0) return firstOperand / secondOperand;
		else return Integer.MAX_VALUE;
	}
}
