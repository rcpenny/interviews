// follow up: merge k sorted list

public class MergeTwoSortedList {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				tail.next = l1;
				l1 = l1.next;
				tail = tail.next;
				continue;
			}
			tail.next = l2;
			l2 = l2.next;
			tail = tail.next;
		}

		if (l1 == null) tail.next = l2;
		if (l2 == null) tail.next = l1;

		return dummy.next;
	}
}
