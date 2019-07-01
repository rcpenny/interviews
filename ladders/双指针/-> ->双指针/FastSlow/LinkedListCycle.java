class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class LinkedListCycle {
  public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    ListNode fast = slow;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast.next == slow) return true;
    }
    return false;
  }
}