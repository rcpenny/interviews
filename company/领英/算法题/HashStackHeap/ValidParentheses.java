// 给定一个字符串所表示的括号序列，包含以下字符： '(', ')', '{', '}', '[' and ']'，
//  判定是否是有效的括号序列。
// 括号必须依照 "()" 顺序表示， "()[]{}" 是有效的括号，但 "([)]" 则是无效的括号。
// leet20

class ValidParentheses {
  public boolean isValid(String s) {
		if (s == null || s.length() == 0) return true;

		char[] chars = s.toCharArray();
		Stack<Character> stack = new Stack<>();

		for (char c : chars) {
			// 注意 in case ")"
			if (stack.isEmpty()) {
				stack.push(c);
				continue;
			}

			if (c == '(' || c =='[' || c == '{') {
				stack.push(c);
				continue;
			}

			if (c == ']') {
				char top = stack.peek();
				if (top != '[') return false;
				else stack.pop();
			}
			
			if (c == ')') {
				char top = stack.peek();
				if (top != '(') return false;
				else stack.pop();
			}
			
			if (c == '}') {
				char top = stack.peek();
				if (top != '{') return false;
				else stack.pop();
			}
		}

		return stack.isEmpty();
  }
}
