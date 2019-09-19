import java.util.ArrayList;
import java.util.List;

// 给定一个包含 m x n 个要素的矩阵，（m 行, n 列），按照螺旋顺序，返回该矩阵中的所有要素。

// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]

// Output: [1,2,3,6,9,8,7,4,5]

class SpiralMatrix {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<Integer>();

    if (matrix == null || matrix.length == 0) return result;

    int m = matrix.length;
    int n = matrix[0].length;

    int x = 0; 
    int y = 0;

    while(m > 0 && n > 0){
      //if one row/column left, no circle can be formed
      if(m == 1){
        for (int i = 0; i < n; i++)
          result.add(matrix[x][y++]);
        break;
      } else if (n == 1) {
        for(int i = 0; i < m; i++)
          result.add(matrix[x++][y]);
        break;
      }

      //below, process a circle

      //top - move right
      for (int i = 0; i < n - 1; i++)
        result.add(matrix[x][y++]);

      //right - move down
      for (int i = 0; i < m - 1; i++)
        result.add(matrix[x++][y]);

      //bottom - move left
      for (int i = 0; i < n - 1; i++)
        result.add(matrix[x][y--]);

      //left - move up
      for(int i = 0; i < m - 1; i++)
        result.add(matrix[x--][y]);

      x++;
      y++;
      m -= 2;
      n -= 2;
    }

    return result;
  }
}