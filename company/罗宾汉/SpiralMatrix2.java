// 给定一个数n, 生成一个包含1~n^2 的螺旋形矩阵.

// 输入: 3
// 输出:
// [
//   [ 1, 2, 3 ],
//   [ 8, 9, 4 ],
//   [ 7, 6, 5 ]
// ]

// 外圈正方形从外往里一个个缩
public class SpiralMatrix2 {

  public int[][] generateMatrix(int n) {
    if (n <= 0) return new int[0][0];

    int[][] matrix = new int[n][n];

    int count = 1;
    int circle = 0; // 旋转了几次
    int x, y, i;

    // square into square
    for (int len = n; len >= 2; len -= 2) {
      // 正方形上边 ->
      x = 0 + circle;
      y = 0 + circle;
      for (i = 1; i <= len - 1; i++) {
        matrix[x][y] = count;
        y++;
        count++;
      }

      // 正方形右边 
      x = 0 + circle;
      y = n - 1 - circle;
      for (i = 1; i <= len - 1; i++) {
        matrix[x][y] = count;
        x++;
        count++;
      }

      // 正方形下边
      x = n - 1 - circle;
      y = n - 1 - circle;
      for (i = 1; i <= len - 1; i++) {
        matrix[x][y] = count;
        y--;
        count++;
      }

      // 正方形左边
      x = n - 1 - circle;
      y = 0 + circle;
      for (i = 1; i <= len - 1; i++) {
        matrix[x][y] = count;
        x--;
        count++;
      }
      
      circle++;
    }

    if (n % 2 == 1) matrix[n/2][n/2] = n * n;

    return matrix;
  }
}