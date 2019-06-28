import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.lintcode.com/problem/friends-within-three-jumps/
 * 
 * 给两个数组a,b,代表a[i]与b[i]是朋友，再给出两个数组c,d，
 * 表示询问c[i]和d[i]是否为三跳之内的朋友。
 * （比如A与B是朋友，B与C是朋友，那么B算A的一跳朋友，C算A的二跳朋友）
 * 
 * 输入: a = [1,2,3,4], b = [2,3,4,5], c = [1,1], d = [4,5]
 * 输出: [1,0]
 * 解释:
 * 1 → 2 → 3 → 4 → 5，4是1的三跳朋友，5是1的四跳朋友
 */
public class FriendsWith3Jumps {
  public int[] withinThreeJumps(int[] a, int[] b, int[] c, int[] d) {
    // key is friend i, value contains all his friends
    HashMap<Integer, Set<Integer>> relations = new HashMap<>();

    for (int i = 0; i < a.length; i++) {
      relations.putIfAbsent(a[i], new HashSet<>());
      relations.putIfAbsent(b[i], new HashSet<>());
      relations.get(a[i]).add(b[i]);
      relations.get(b[i]).add(a[i]);
    }

    // start bfs
    int[] jumps = new int[c.length];
    for (int jump = 0; jump < jumps.length; jump++) {
      jumps[jump] = searchJumps(c[jump], d[jump], relations);
    }
    return jumps;
  }

  private int searchJumps(int a, int b, HashMap<Integer, Set<Integer>> relations) {
    if (!relations.containsKey(a) || !relations.containsKey(b)) return 0;

    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visisted = new HashSet<>();
    queue.offer(a);
    visisted.add(a);

    int jump = 0;
    while (jump < 3 && !queue.isEmpty()) {
      int size = queue.size();
      jump++;
      for (int i = 0; i < size; i++) {
        int tmp = queue.poll();    
        for (int friend : relations.get(tmp)) {
          if (visisted.contains(friend)) continue;
          if (friend == b) return 1;
          queue.offer(friend);
          visisted.add(friend);
        }
      }
    }
    return 0;
  }
}