import java.util.ArrayList;

// 给定一个单词数组和一个宽度maxWidth，格式化文本，使每行具有刚好maxWidth个字符并完全（左和右）对齐。
// 你应该用贪心法打包你的单词; 也就是说，在每行中包含尽可能多的单词。 必要时填充额外的空格，以便每行具有正确的maxWidth字符。
// 单词之间的额外空格应尽可能均匀分布。 如果一行上的空格数不均匀分配，则左侧的空插槽将分配比右侧插槽更多的空格。
// 对于最后一行文本，它应该是左对齐的，并且在单词之间不插入额外的空格。

// 输入:
// words = ["What","must","be","acknowledgment","shall","be"]
// maxWidth = 16
// 输出:
// [
//   "What   must   be",
//   "acknowledgment  ",
//   "shall be        "
// ]
// 说明：请注意，最后一行是 "shall be    " 而不是 "shall     be"，
//               因为最后一行必须左对齐而不是完全对齐。
//               请注意，第二行也是左对齐的，因为它只包含一个单词

//leet68
public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> text = new ArrayList<>();

    if (words == null || words.length == 0) {
      return text;
    }

    int curLen = 0;
    List<String> curWords = new ArrayList<>();

    for (String word : words) {

      // 1. don't assemble words, add words greedyly to a line
      if (curWords.isEmpty() || curLen + curWords.size() + word.length() <= maxWidth) {
        curWords.add(word);
        curLen += word.length();
        continue;
      }

      // 2. assemable words
      int spaceCount = maxWidth - curLen;
      int interval = curWords.size() - 1;
      StringBuilder sb = new StringBuilder();

        // only 1 word
      if (interval == 0) {
        sb.append(curWords.get(0));
        for (int i = 0; i < spaceCount; i++) sb.append(" ");
      }

        // more than 1 word
      else {
        int extrasSpace = spaceCount % interval;
        int spaceEachInterval = spaceCount / interval;

        String spaces = "";
        for (int k = 0; k < spaceEachInterval; k++) {
          spaces += " ";
        }

        sb.append(curWords.get(0));

        for (int j = 1; j < curWords.size(); j++) {
          sb.append(spaces);
          if (extrasSpace != 0) {
            sb.append(" ");
            extrasSpace--;
          }
          sb.append(curWords.get(j));
        }
      }


      // 3. after assemble, add line to text,
      text.add(sb.toString());
      curLen = word.length();
      curWords.clear();
      curWords.add(word);
    }


    // take care of last line
    StringBuilder sb = new StringBuilder();
    sb.append(curWords.get(0));

    for (int j = 1; j < curWords.size(); j++) {
      sb.append(" ");
      sb.append(curWords.get(j));
    }

    while (sb.length() < maxWidth) {
      sb.append(" ");
    }
    text.add(sb.toString());

    return text;
  }
}
