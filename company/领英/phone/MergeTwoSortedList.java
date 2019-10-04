// follow up: merge k sorted list
// lc21

public class MergeTwoSortedList {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// 生成List的方法 dummy, tail :)
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		// 重点：先全不为空，再把非空的append上去
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				tail.next = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
			}
			tail = tail.next;
		}

		if (l1 == null) tail.next = l2;
		if (l2 == null) tail.next = l1;

		return dummy.next;
	}
}
