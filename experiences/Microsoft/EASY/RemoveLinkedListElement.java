// Remove all elements from a linked list of integers that have value val
// Input: head = 1->2->3->3->4->5->3->null, val = 3
// Output: 1->2->4->5->null

public class RemoveLinkedListElement {
  public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy; // head指回dummy用来处理第一个node就是val的情况

		while (head.next != null) {
			if (head.next.val == val) head.next = head.next.next;
			else head = head.next;
		}

		return dummy.next;
	}
}
