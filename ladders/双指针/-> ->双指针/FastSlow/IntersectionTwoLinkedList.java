/**
 * ind the node at which the intersection of two singly linked lists begins.
 * 
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 */
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class IntersectionTwoLinkedList {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;

		ListNode tmpA = headA, tmpB = headB;
		int lengthA = 0, lengthB = 0;

		while (tmpA != null) {
			tmpA = tmpA.next;
			lengthA++;
		}
		while (tmpB != null) {
			tmpB = tmpB.next;
			lengthB++;
		}

		tmpA = headA;
		tmpB = headB;
		int diff = Math.abs(lengthA - lengthB);
		if (lengthA >= lengthB) {
			for (int i = 0; i < diff; i++) tmpA = tmpA.next;
		} else {
			for (int i = 0; i < diff; i++) tmpB = tmpB.next;
		}

		// move forward together
		while (tmpA != null && tmpB != null) {
			if (tmpA == tmpB) return tmpA;
			tmpA = tmpA.next;
			tmpB = tmpB.next;
		}

		return null;
	}
}
