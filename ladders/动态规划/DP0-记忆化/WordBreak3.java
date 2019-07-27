import java.util.HashSet;

// 给出一个单词表和一条去掉所有空格的句子，根据给出的单词表添加空格, 
// 返回可以构成的句子的数量, 保证构成的句子中所有的单词都可以在单词表中找到.

// 输入：
// "CatMat"
// ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
// 输出： 3
// 解释：
// 我们可以有如下三种方式：
// "CatMat" = "Cat" + "Mat"
// "CatMat" = "Ca" + "tM" + "at"
// "CatMat" = "C" + "at" + "Mat"

// Ignore case

// 坐标型
// 1. 确定状态 f[i][j] 
// 2. 转移方程 
// 3. 初态边界  
// 4. 顺序计算

public class WordBreak3 {
  public int wordBreak3(String s, Set<String> dict) {
    int n = s.length();

    String lower_s = s.toLowerCase();
    Set<String> lower_dict = new HashSet<>();
    for (String str : dict) lower_dict.add(str.toLowerCase());

    int[][] f = new int[n][n];
    // 初始化
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        String sub = lower_s.substring(i, j + 1);
        if (lower_dict.contains(sub)) 
          f[i][j] = 1;
      }
    }

    for (int i = 0; i < n; i++)
      for (int j = i; j < n; j++)
        for (int k = i; k < j; k++)
          f[i][j] += f[i][k] * f[k + 1][j];

    return f[0][n - 1];
  }
}
