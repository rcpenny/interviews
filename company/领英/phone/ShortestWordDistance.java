// 给出一个单词列表和两个单词单词1,单词2，返回列表中这两个单词之间的最短距离。

// 输入：["practice", "makes", "perfect", "coding", "makes"],"makes","coding"
// 输出：1
// 解释：index("makes") - index("coding") = 1

// 您可以假定单词1不等于单词2，而单词1和单词2在列表中都存在。

// 打擂台思想
// lc243

public class ShortestWordDistance {
  public int shortestDistance(String[] words, String word1, String word2) {
    int p1 = -1;
    int p2 = -1;
    int shortest = words.length;
    
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      
      if (word.equals(word1)) {
        p1 = i;
      }

      if (word.equals(word2)) {
        p2 = i;
      }

      if (p1 != -1 && p2 != -1) {
        shortest = Math.min(shortest, Math.abs(p1 - p2));
      }
    }
    
    return shortest;
  }
}