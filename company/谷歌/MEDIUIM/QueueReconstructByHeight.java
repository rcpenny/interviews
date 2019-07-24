import java.util.Comparator;

// 假设你有一个顺序被随机打乱的列表，代表了站成一列的人群。每个人被表示成一个二元组(h, k)，
// 其中h表示他的身高，k表示站在他之前的身高高于或等于h的人数。你需要将这个队列重新排列以恢复其原有的顺序

// 输入：[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
// 输出： [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


// # [[7, 0]]
// # [[7, 0], [7, 1]]
// # [[7, 0], [6, 1], [7, 1]]
// # [[5, 0], [7, 0], [6, 1], [7, 1]]
// # [[5, 0], [7, 0], [5, 2], [6, 1], [7, 1]]
// # [[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]

// 因为插入person 的时候，比这个person高的人都已经放进去了，
// 那么如果比person高的人有x个，就可以直接插入到第x的位置上就行了。

public class QueueReconstructByHeight {

  // 先比h,h大在前，再比k,k小在前
  private Comparator<int[]> cpt = new Comparator<int[]>() {
    @Override public int compare(int[] a, int[] b) {
      if (a[0] != b[0]) return b[0] - a[0];
      return a[1] - b[1];
    }
  };

  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, cpt);

    List<int[]> result = new LinkedList<>();
    for(int[] cur : people) result.add(cur[1], cur);
    
    return result.toArray(new int[people.length][]);
  }
}
