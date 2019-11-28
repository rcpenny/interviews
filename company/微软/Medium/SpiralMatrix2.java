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
		if (n < 1) return new int[0][0];

		int[][] matrix = new int[n][n];
		int x = 0, y = 0, i = 0;
		int count = 1;
		int len = n;

		while (len > 0) {
			for (i = 0; i < len - 1; i++) {
				matrix[x][y++] = count;
				count++;
			}

			for (i = 0; i < len - 1; i++) {
				matrix[x++][y] = count;
				count++;
			}

			for (i = 0; i < len - 1; i++) {
				matrix[x][y--] = count;
				count++;
			}

			for (i = 0; i < len - 1; i++) {
				matrix[x--][y] = count;
				count++;
			}

			x++;
			y++;
			len -= 2;
		}

		if (n % 2 != 0) matrix[n/2][n/2] = count;

		return matrix;
  }
}
