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
    List<Integer> result = new ArrayList<>();

    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
      return result;
    
    // 向右，下，左，上
    int[] direction = {0,1,2,3};

    int m = matrix.length;
    int n = matrix[0].length;

    int d = 0;
    
    int i = 0, j = 0, count = 0;
    while (count < m * n) {
        // keep going within current direction until being out of border
        while (i >= 0 && i < m && j >= 0 && j < n && matrix[i][j] != Integer.MIN_VALUE) {
            res.add(matrix[i][j]);
            matrix[i][j] = Integer.MIN_VALUE; // mark as processed
            count++;
            
            if (direction == 0) j++; // go right
            else if (direction == 1) i++; // go down
            else if (direction == 2) j--; // go left
            else if (direction == 3) i--; // go up
        }
        // out of border: change direction
        direction = (direction + 1) % 4;
        
        // need to adjust to next valid number
        if (direction == 1) { // down
            j--;
            i++;
        } else if (direction == 2) { // left
            i--;
            j--;
        } else if (direction == 3) { // up
            j++;
            i--; 
        } else if (direction == 0) { // right
            i++;
            j++;
        }
    }
    
    return result;
  }
}