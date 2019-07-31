// searches for a value in an m x n matrix, return the occurrence of it.

// This matrix has the following properties:
// Integers in each row are sorted from left to right.
// Integers in each column are sorted from up to bottom.
// No duplicate integers in each row or column.

// Input:
//     [
//       [1, 3, 5, 7],
//       [2, 4, 7, 8],
//       [3, 5, 9, 10]
//     ]
//     target = 3
// Output:2

// 从左下角3开始，往右上角逼近
// 大于3, 向右（扔掉比他小的这一column）
// 小于3，向上（扔掉比他大的这一row）

// (变相二分，选择向上或向右) 选择左下角，相当于二分法选了一个middle值
// 从右上开始也可以

public class Search2DMatrix2 {

  public int searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) return 0;

    int occur = 0;

    // 从右上角开始搜
    int m = 0;                     // row index
    int n = matrix[0].length - 1;  // col index

    while (m < matrix.length && n >= 0) {
      if (matrix[m][n] > target) n--;
      else if (matrix[m][n] < target) m++;
      else {
        n--;
        m++;
        occur++;
      } 
    }

    return occur;
  }
}