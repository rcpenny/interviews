// Write an efficient algorithm that searches for a value in an m x n matrix, 
// return the occurrence of it.

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
// 从左下角开始，往右上角逼近

public class Search2DMatrix2 {
  public int searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) return 0;
    if (matrix[0] == null || matrix[0].length == 0) return 0;

    int r = matrix.length - 1;
    int c = 0;
    int count = 0;

    while (r >= 0 && c < matrix[0].length) {
      if (matrix[r][c] < target) c++;
      else if (matrix[r][c] > target) r--;
      else {
        r--;
        c++;
        count++;
      }
    }

    return count;
  }
}