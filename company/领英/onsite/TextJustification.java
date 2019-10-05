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

public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> res = new ArrayList<>();
    if (words == null || words.length == 0) return res;
    
    List<String> list = new ArrayList<>();
    
    int len = 0;

    for (String word: words) {
      // 加words: 单词总长 + 空格数（最少保证一个空格) + 当前单词 <= maxWidth
      if (list.isEmpty() || len + list.size() + word.length() <= maxWidth) {
          list.add(word);
          len += word.length();
          continue;
      }

      // 拼words
      StringBuilder sb = new StringBuilder();
      int spaceCnt = maxWidth - len;
      int interval = list.size() - 1;

      // 一个单词
      if (interval == 0) {
        sb.append(list.get(0));
        for (int j = 0; j < spaceCnt; ++j) {
            sb.append(" ");
        }   
      } else {
        int extraSpace = spaceCnt % interval;
        int spacesEachInterval = spaceCnt / interval;
        
        String spaces = "";
        for (int k = 0; k < spacesEachInterval; ++k) {
            spaces += " ";
        }
        
        sb.append(list.get(0));
        for (int j = 1; j < list.size(); ++j) {
          sb.append(spaces);
          if (extraSpace != 0) {
              sb.append(" ");
              extraSpace--;
          }
          sb.append(list.get(j));
        }
      }
          
      res.add(sb.toString());
      len = word.length();
      list = new ArrayList<>();
      list.add(word);
    }
    
    //last line
    StringBuilder sb = new StringBuilder();
    int spaceCnt = maxWidth - len;
    sb.append(list.get(0));
    for (int j = 1; j < list.size(); ++j) {
        sb.append(" ");
        sb.append(list.get(j));
    }
    
    int spaceLeft = maxWidth - sb.length();
    for (int k = 0; k < spaceLeft; ++k) {
        sb.append(" ");
    }
    res.add(sb.toString());
    
    return res;
  }
}
