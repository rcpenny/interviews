// 实现一个基础的计算器来计算一个简单表达式。
// 这个表达式字符串可能包含左括号 '(' 与右括号 ')'，加号 '+' 或者 减号 '-'，非负整数以及空格 ' '。
// 给出的表达式总是合理的。

// 输入："(1+(4+5+2)-3)+(6+8)" 
// 输出：23

public class BasicCalculator {
  public int calculate(String s) {
		char[] array = s.toCharArray();
		Stack<Object> stack = new Stack<>();

		int num = 0;
		for (int i = 0; i < array.length; i++) {
			char cur = array[i];

			if (cur == ' ') continue;
			if (Character.isDigit(cur)) {
				num = num * 10 + (cur - '0');	
				continue;
			}

			// case when char is ( ) + -
			if (cur == '(') {
				stack.push(c)
			}
		}
	}
}
