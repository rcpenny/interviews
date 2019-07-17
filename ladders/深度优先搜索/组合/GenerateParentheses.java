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

  // 
  // 定义：元数据n  控制used：加过几个'('， 控制left:')'append后还剩几个'('  状态comb 结果combs
  private void generate(int n, int left, int used, StringBuilder comb, List<String> combs) {
    // 出口：不可以是used = n
    if (comb.length() == 2 * n) {
      combs.add(comb.toString());
      return;
    }

    // 拆解：可加'('
    if (used < n) {
      comb.append('(');
      generate(n, left + 1, used + 1, comb, combs);
      comb.deleteCharAt(comb.length() - 1);
    }
    
    // 拆解：可加')'
    if (left > 0) {
      comb.append(')');
      generate(n, left - 1, used, combs);
      comb.deleteCharAt(comb.length() - 1);
    }
  }
}
