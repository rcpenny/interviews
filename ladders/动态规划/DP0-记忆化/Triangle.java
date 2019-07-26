// 给定一个数字三角形，找到从顶部到底部的最小路径和。每一步可以移动到下面一行的相邻数字上。

public class Triangle {
  public int minimumTotal(int[][] triangle) {
    int row = triangle.length;

    for (int i = row - 2; i >= 0; i--) {
      for (int j = 1; j < triangle[i + 1].length; j++) {
        triangle[i][j - 1] += Math.min(triangle[i + 1][j - 1], triangle[i + 1][j]);
      }
    }

    return triangle[0][0];
  }
}