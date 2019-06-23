import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.lintcode.com/problem/sliding-puzzle/
 * https://www.lintcode.com/problem/sliding-puzzle-ii/
 * 
 * 在一块大小为 2x3 的板上，有 5 块瓦片，分别用整数 1 到 5 表示，还有一块空地用 0 表示。
 * 一次移动表示 0 与其相邻的四个方向之一的数字交换位置。
 * 当且仅当 这块板 上的瓦片摆放状态为 [[1,2,3],[4,5,0]] 时，才能说这块板存在的问题被解决了。
 * 给定初始的板子排布，返回到目标排布最少的移动次数。
 * 如果不能从初始排布移动到目标排布，返回-1.
 */

public class SlidingPuzzle {
  private final String target = "123450";
  private int row;
  private int col;

  private int[] dx = {1, -1, 0, 0};
  private int[] dy = {0, 0, -1, 1};

  public int slidingPuzzle(int[][] board) {
    row = board.length;
    col = board[0].length;
    Queue<String> queue = new LinkedList<>();
    Set<String> visisted = new HashSet<>();

    // add initial state to queue
    String initialState = "";
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        initialState += String.valueOf(board[i][j]);
      }
    }

    if (initialState.equals(target)) return 0;

    int moves = 1;
    queue.offer(initialState);
    visisted.add(initialState);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String state = queue.poll();
        List<String> nextStates = getNextStates(state, visisted);
        for (String nextState : nextStates) {
          if (nextState.equals(target)) return moves;
          //这两步忘写了。bfs先把大模板搭好再去写小方程
          visisted.add(nextState);
          queue.offer(nextState);
        }
      }
      moves++;
    }

    return moves;
  }

  private List<String> getNextStates(String state, Set<String> visisted) {
    List<String> nextStates = new ArrayList<>();
    int x = state.indexOf('0') / 3;
    int y = state.indexOf('0') % 3;
    for (int i = 0; i < 4; i++) {
      char[] array = state.toCharArray();
      int x_ = x + dx[i];
      int y_ = y + dy[i];
      if (0 <= x_ && x_ < row && 0 <= y_ && y_ < col) {
        String tmp = stateAfterSwap(array, x, y, x_, y_);
        if (!visisted.contains(tmp)) nextStates.add(tmp);
      }
    }
    return nextStates;
  }

  private String stateAfterSwap(char[] array, int x, int y, int x_, int y_) {
    char tmp = array[x * 3 + y];
    array[x * 3 + y] = array[x_ * 3 + y_];
    array[x_ * 3 + y_] = tmp;
    // array.toString()是错误的！！！返回了reference
    return String.valueOf(array);
  }
}