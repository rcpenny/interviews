// 实现一个基础的计算器来计算一个简单表达式。
// 这个表达式字符串可能包含左括号 '(' 与右括号 ')'，加号 '+' 或者 减号 '-'，非负整数以及空格 ' '。
// 给出的表达式总是合理的。

// 输入："(1+(4+5+2)-3)+(6+8)" 
// 输出：23

// 学习decode string
class BasicCalculator {
	public int calculate(String s) {
		Stack<Integer> stack = new Stack<Integer>();

		int operand = 0;
		int result = 0; // For the on-going result
		int sign = 1;  // 1 means positive, -1 means negative

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				operand = 10 * operand + (ch - '0');
			} 
			
			else if (ch == '+') {
				result += sign * operand;
				sign = 1;
				operand = 0;
			} 
			
			else if (ch == '-') {
				result += sign * operand;
				sign = -1;
				operand = 0;
			} 
			
			else if (ch == '(') {
				stack.push(result);
				stack.push(sign);
				sign = 1;
				result = 0;
			} 
			
			else if (ch == ')') {
				result += sign * operand;
				result *= stack.pop();
				result += stack.pop();
				operand = 0;
			}
		}
		return result + (sign * operand);
	}
}
