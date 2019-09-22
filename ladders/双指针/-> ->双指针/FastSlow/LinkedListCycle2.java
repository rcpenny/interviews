// 如果存在cycle, return 交叉点

public class LinkedListCycle2 {
  public ListNode detectCycle(ListNode head) {
    if (head == null) return null;

    ListNode fast = head;
    ListNode slow = head;
		ListNode intersection = head;

    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
			slow = slow.next;

      if (fast == slow) {
				// 套圈，画图看, 可证明距离
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
