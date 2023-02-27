import java.util.ArrayList;

/**
 * Input: 3
 * Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
 */

 // leet22
 
public class GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();

    generate(new StringBuilder(), n, 0, 0, result);

    return result;
  }

  private void generate(StringBuilder sb, int n, int leftUsed, int rightUsed, List<String> result) {
    if (sb.length() == n * 2) {
      result.add(sb.toString());
      return;
    }

    // add left
    if (leftUsed < n) {
      sb.append('(');
      generate(sb, n, leftUsed + 1, rightUsed, result);
      sb.deleteCharAt(sb.length() - 1);
    }

    // add right
    if (rightUsed < n && rightUsed < leftUsed) {
      sb.append(')');
      generate(sb, n, leftUsed, rightUsed + 1, result);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
