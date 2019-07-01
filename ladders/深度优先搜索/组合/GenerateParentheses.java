import java.util.ArrayList;

/**
 * Given n, and there are n pairs of parentheses, 
 * write a function to generate all combinations of well-formed parentheses.
 * 
 * Input: 3
 * Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
 */
public class GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    List<String> combs = new ArrayList<>();
    if (n <= 0) return combs;

    generate(n, 0, 0, new StringBuilder(), combs);
    return combs;
  }

  // left is number of left parentheses(deleted by right paren)
  // used is the total number of left paren used.
  private void generate(int n, int left, int used, StringBuilder comb,
    List<String> combs) {
    if (comb.length() == 2 * n) {
      combs.add(comb.toString());
      return;
    }

    if (used < n) {
      comb.append('(');
      generate(n, left + 1, used + 1, comb, combs);
      comb.deleteCharAt(comb.length() - 1);
    }
    
    if (left > 0) {
      comb.append(')');
      generate(n, left - 1, used, combs);
      comb.deleteCharAt(comb.length() - 1);
    }
  }
}
