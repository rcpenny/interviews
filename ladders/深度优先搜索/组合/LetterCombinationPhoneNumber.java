import java.util.ArrayList;

/**
 * 给一个不包含'0'和'1'的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。
 * 下图的手机按键图，就表示了每个数字可以代表的字母
 * 
 * 输入: "23"
 * 输出: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 * 解释: 
 * '2' 可以是 'a', 'b' 或 'c'
 * '3' 可以是 'd', 'e' 或 'f'
 */

public class LetterCombinationPhoneNumber {
  private final String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  public List<String> letterCombinations(String digits) {
    List<String> combinations = new ArrayList<>();

    if (digits == null || digits.length() == 0) return combinations;
    
    char[] digitsChar = digits.toCharArray();
    combineDigits(digitsChar, 0, new StringBuilder(), combinations);

    return combinations;
  }

  private void combineDigits(char[] digits, int index, StringBuilder comb, List<String> combinations) {
    if (index == digits.length) {
      combinations.add(comb.toString());
      return;
    }

    int digit = digits[index] - '0';

    for (int i = 0; i < letters[digit].length(); i++) {
      char tmp = letters[digit].charAt(i);

      comb.append(tmp);
      combineDigits(digits, index + 1, comb, combinations);
      comb.deleteCharAt(comb.length() - 1);
    }
  }
}
