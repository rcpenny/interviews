public class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class MiddleLinkedList {
  public ListNode middleNode(ListNode head) {
    if (head == null) return head;

    ListNode fast = head;
    ListNode slow = head;

    // 画图
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }
}