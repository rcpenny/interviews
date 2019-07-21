class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

// 如果存在cycle, return 交叉点
public class DetectCycle {
  public ListNode detectCycle(ListNode head) {
    if (head == null) return null;

    ListNode fast = head;
    ListNode slow = head;
    ListNode intersection = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
			slow = slow.next;

      if (fast == slow) {
				// 套圈，画图看
        while (intersection != slow) {
          intersection = intersection.next;
          slow = slow.next;
        }
        return intersection;
      }
    }
    return null;
  }
}
