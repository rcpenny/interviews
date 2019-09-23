import java.util.Stack;

// 实现一个基础计算器来计算一个简单表达式字符串。
// 这个表达式字符串只包含 非负 整数，运算符 +, -, *, / 以及空格 。 整数除法应该舍去小数
// 你可以假设给出的表达式总是合理的。

// 输入:"3+2*2"
// 输出:7

public class BasicCalculator2 {
  public int calculate(String s) {
    if (s == null || s.length() == 0) return 0;

    Stack<Integer> stack = new Stack<Integer>();
    
    int num = 0;
    char sign = '+';
      
    for(int i = 0; i < s.length(); i ++) {
      char ch = s.charAt(i);
      
      if (Character.isDigit(ch))
        num = num * 10 + ch - '0';

      if ((!Character.isDigit(ch) && ' ' != ch) || i == s.length() - 1) {
        if(sign == '-') stack.push(-num);
        else if(sign == '+') stack.push(num);
        else if(sign == '*') stack.push(stack.pop() * num);
        else if(sign == '/') stack.push(stack.pop() / num);
        
        sign = s.charAt(i);
        num = 0;
      }
    }
  
    int result = 0;
    for (int i : stack) result += i;
    
    return result;
  }  
}
