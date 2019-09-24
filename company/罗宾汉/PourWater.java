// 给定一个评估图， heights[i] 表示该地的高度。所有下标对应的地面宽度均为 1。
// 请问V个单位的水落在下标为 K 的地面之后，每个下标对应的地面水量是多少？

// 水首先从下标K降落，接着它依据下述规则流动：

// 首先，液滴不能流向更高的地方；
// 如果液滴能向左最终能下降，那么向左移动；
// 否则，如果能向右最终能下降，则向右移动；
// 否则，留当前位置。
// 其中，“最终能下降”意味着如果液滴像那个方向移动，最终能够处于更低高度。

// 假设整个区域左右两侧有无限高的地形。此外，一单位水不能拆分，即每个单位必须恰好落在一个区块内。

public class PourWater {
  public int[] pourWater(int[] heights, int V, int K) {
    if (heights == null || heights.length == 0 || V == 0) return heights;
    
    for (int m = 0; m < V; m++) {
      // index代表当前最低点
      int index = K;
      
      // 向左搜
      for (int i = K - 1; i >= 0; i--) {
        if (heights[i] > heights[index]) break;
        if (heights[i] < heights[index]) index = i;
        // if heights[i] == heights[index], 保持原最低点index
      }
      
      if (index != K) {
        heights[index]++;
        continue;
      }
      
      // 向右搜
      for (int i = K + 1; i < heights.length; i++) {
        if (heights[i] > heights[index]) break;
        if (heights[i] < heights[index]) index = i;
        // if heights[i] == heights[index], 保持原最低点index
      }

      heights[index]++;
    }

    return heights;
  }
}