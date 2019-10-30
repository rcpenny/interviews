// 给定一个N×N的二维矩阵表示图像，90度顺时针旋转图像
// lint161

public class RotateImage {
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return;
    }
    
    // 1. 上下镜像交换 1st and last row
    int n = matrix.length;
    for (int i = 0; i < n / 2; i++) // half row
      for (int j = 0; j < n; j++)   // full column
        swapByPosition(matrix, i, j, n - 1 - i, j);
        
    // 2. 左下角与右上角镜像交换
    for (int i = 0; i < n; i++) // j to i, i to j
      for (int j = i; j < n; j++)
        swapByPosition(matrix, i, j, j, i);
  }
  
  private void swapByPosition(int[][] matrix, int ax, int ay, int bx, int by) {
    int tmp = matrix[ax][ay];
    matrix[ax][ay] = matrix[bx][by];
    matrix[bx][by] = tmp;
  }
}