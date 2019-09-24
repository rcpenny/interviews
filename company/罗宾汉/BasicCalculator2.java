import java.util.Stack;

// 实现一个基础计算器来计算一个简单表达式字符串。
// 这个表达式字符串只包含 非负 整数，运算符 +, -, *, / 以及空格 。 整数除法应该舍去小数
// 你可以假设给出的表达式总是合理的。

// 输入:"3+2*2"
// 输出:7

public class BasicCalculator2 {
  public int calculate(String s) {
    if (s == null || s.length() == 0) return 0;
    
    Stack<Integer> stack = new Stack<>();
		
		// 第一个sign by default +
    char sign = '+';
    
    int number = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      
      if (Character.isDigit(c)) {
        number = number * 10 + c - '0';
      }
			
			// 最后一个字符，铁要扔进去计算。因为一定是number 不能再继续放在上个if里面判断了
      if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
        if (sign == '+') stack.push(number);
        if (sign == '-') stack.push(-number);
        
        if (sign == '*') stack.push(stack.pop() * number);
        if (sign == '/') stack.push(stack.pop() / number);
        
        sign = c;
        number = 0;
      }
    }
    
    int result = 0;
    for (int i : stack) result += i;
    
    return result;
  }
}
