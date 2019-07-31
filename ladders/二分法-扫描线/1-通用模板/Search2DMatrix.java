// 写出一个高效的算法来搜索 m × n矩阵中的值。

// 这个矩阵具有以下特性：

// 每行中的整数从左到右是排序的。
// 每行的第一个数大于上一行的最后一个整数

// consider entire matrix as an array
public class Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) return false;  
    if (matrix[0] == null || matrix[0].length == 0) return false;

    int row = matrix.length;
    int col = matrix[0].length;
    int start = 0, end = row * col - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      int number = matrix[mid / col][mid % col];
      if (number == target) return true;
      else if (number < target) start = mid;
      else end = mid;
    }
    if (matrix[start / col][start % col] == target ||
      matrix[end / col][end % col] == target) return true;
    return false;
  }
}