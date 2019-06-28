class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

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