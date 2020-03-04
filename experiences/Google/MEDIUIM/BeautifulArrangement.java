import java.util.ArrayList;
import java.util.List;

// 假设你有 N 个从 1 到 N 的整数。我们将一个美丽的排列定义为：如果一个数组由这给出的 N 个整数构成，
// 且满足下列任意一个条件对此数组中的第i个位置（1 <= i <= N）的要求，则这个数组为美丽的排列：

// 第 i 个 位置的元素可以被 i 整除。
// i 可以被第 i 个元素整除。

// 现在给出 N，你可以构造出多少美丽的排列？
// N 是一个正整数并且不会超过 15。

// 暴力排列解法
public class BeautifulArrangement {
  private int count = 0;

  public int countArrangement(int N) {
    int[] array = new int[N];
    for (int i = 0; i < N; i++) array[i] = i + 1; 
    boolean[] visited = new boolean[N];

    dfs(array, 0, visited, new ArrayList<>());

    return count;
  }

  // 定义： 元数据array(1-N)  控制index(为了查beatiful)，visited(为排列)   状态arrange 
  private void dfs(int[] array, int index, boolean[] visited, List<Integer> arrange) {
    // 出口：合理arrangement出现
    if (arrange.size() == array.length) {
      count++;
      return;
    }

    // 拆解：1-N一个个扫
    for (int i = 0; i < array.length; i++) {
      // 进入递归的条件：数字没选过，符合beatiful
      if (visited[i]) continue;
      if (!beautiful(array[i], index + 1)) continue;

      visited[i] = true;
      arrange.add(array[i]);
      dfs(array, index + 1, visited, arrange);
      visited[i] = false;
      arrange.remove(arrange.size() - 1);
    }
  }

  private boolean beautiful(int number, int position) {
    if (number % position == 0) return true;
    if (position % number == 0) return true;
    return false;
  }
}
