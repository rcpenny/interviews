// 找到Linkedlist的中间node

public class MiddleLinkedList {
  public ListNode middleNode(ListNode head) {
    if (head == null) return head;

    ListNode fast = head;
    ListNode slow = head;

    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
		}

    return slow;
  }
}
