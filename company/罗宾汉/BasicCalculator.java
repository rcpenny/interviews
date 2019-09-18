// 实现一个基础的计算器来计算一个简单表达式。
// 这个表达式字符串可能包含左括号 '(' 与右括号 ')'，加号 '+' 或者 减号 '-'，非负整数以及空格 ' '。
// 给出的表达式总是合理的。

// 输入："(1+(4+5+2)-3)+(6+8)" 
// 输出：23

@Todo("debug")
public class BasicCalculator {
  public int calculate(String s) {
		List<Object> symbols = generateSymbols(s);
		Stack<Object> stack = new Stack<>();

		for (Object o : symbols) {
			if (o instanceof Character && o.equals(')'))
				caculate(stack);
			else
				stack.push(o);
		}

		return (int) stack.pop();
	}

	private List<Object> generateSymbols(String s) {
		List<Object> symbols = new ArrayList<>();

		int index = 0;
		while (index < s.length()) {
			//是空格
			char tmp = s.charAt(index);
			if (tmp == ' ') {
				index++;
				continue;
			}

			// 是符号
			if (tmp == '(' || tmp == ')' || tmp == '+' || tmp == '-') {
				symbols.add((Character) tmp);
				index++;
				continue;
			}

			// 是数字
			int left = index;
			while (index < s.length()) {
				char c = s.charAt(j);
				if (Character.isDigit(c)) {
					index++;
					continue;
				}
			}

			Integer i = Integer.valueOf(s.substring(left, index));
			symbols.add(i);
		}

		return symbols;
	}

	private void caculate(Stack<Object> stack) {
		Integer num = 0;
		while (!stack.peek().equals('(')) {
			// pop number
			Object p = stack.pop();

			// pop sign if it has
			if (stack.peek().equals('(')) {
				num += stack.pop();
			} else {
				Object sign = stack.pop();
				if (sign.equals('+')) num += (Integer) p;
				else num -= (Integer) p;
			}
		}

		stack.push(num);
	}
}
