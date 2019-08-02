import java.util.Arrays;

// 在x轴上分布着n个石子，用arr数组来表示它们的位置。现在要求把这些石子移动到1，3，5，7，2n-1 或者 2，4，6，8，2n。
// 也就是说，要求这些石子移动到从1开始连续的奇数位，或从2开始连续的偶数位上。返回最少移动的次数。
// 每次只可以移动1块石头，只能把石头往左移动一个单位或往右移动一个单位。同一个位置不能同时有两块石头。

// 输入: arr = [5,4,1]
// 输出: 1
// 解释: 
// 你只需要把位置4的石子往左移动到3，
// [1,3,5],符合要求。

// 输入:arr = [1,6,7,8,9]
// 输出: 5
// 解释: 
// 最优的移动方案为把1移动到2，把6移动到4，把7移动到6，把9移动到10。
// 花费为1+2+1+1=5。

// lint1585

// 1≤n≤10000
// 1 \leq arr[i] \leq 1000001≤arr[i]≤100000

public class MovingStones {
  public int movingStones(int[] a) {
    Arrays.sort(a);

    int n = a.length;

    int odd_move = 0;
    for (int i = 0; i < n; i++)
      odd_move = odd_move + Math.abs(a[i] - (2 * i + 1));

    int even_move = 0;
    for (int i = 0; i < n; i++)
      even_move = even_move + Math.abs(a[i] - (2 * i + 2));

    return Math.min(odd_move, even_move);
  }
}