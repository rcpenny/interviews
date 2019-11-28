// 假设你和 n 个人在一个聚会中(标记为 0 到 n - 1)，其中可能存在一个名人。
// 名人的定义是所有其他 n - 1 人都认识他/她，但他/她不知道任何一个。
// 现在你想要找出这个名人是谁或者验证这个名人不存在
// 你唯一可以做的事情就是提出如下问题：“你好，A，你认识B吗？” 来获取A是否认识B。
// 你得到一个辅助函数 bool know(a，b)，它会告诉你A是否知道B.
// 实现一个函数 int findCelebrity(n)，你的函数应该使 knows 的调用次数最少。

// 如果在这个聚会中有名人， 那么有且只有一个。如果有名人在聚会中则返回名人的标签，如果没有名人，返回 -1
// leet277

// 1.先提供O(n^2)思路，暴力check 每个人作为candidate，查看其他人是否know他

// need some way to remove potential candidates

// 2. O(n) && O(1)   
// knows(a, b) is true, a is not celebrity
// knows(a, b) is false, b is not celebrity
// either way we could erase one from the potential set

public class FindCelebrity {
  // O(n) && O(1)
  public int findCelebrity(int n) {
    if (n <= 0) return -1;
    if (n == 1) return 0;

		// 因为有且只有一个，所以loop一遍找到这个possible candidate
    int candi = 0;
    for (int i = 1; i < n; i++) {
			// key point: 如果 candi认识其他人，直接pass, i被其他人认识，也被pass
      if (knows(candi, i)) candi = i; 
		}

    for (int i = 0; i < n; ++i) {
      if (i == candi) continue;
      if (knows(candi, i) || !knows(i, candi)) return -1;
		}
		
    return candi;
  }

  // O(n^2)
  public int findCelebrity3(int n) {
    // i is the potential candidate
    for (int i = 0; i < n; i++) {

      int count = 0;
      for (int j = 0; j < n; j++) {
        if (i == j) continue;

        if (!knows(j, i) || knows(i, j)) break;
        count++;
      }

      if (count == n - 1) return i;
    }

    return -1;
  }
}
