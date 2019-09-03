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

	// headA与headB可能有环的情况
	public ListNode getIntersection(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;

		// 先检查有没有环
		ListNode a_inter = detectCycle(headA);
		ListNode b_inter = detectCycle(headB);

		if (a_inter == null && b_inter == null) {
			return getIntersectionNode(headA, headB);
		}
	}

	// headA与headB无环的情况
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
		if (lengthA >= lengthB)
			for (int i = 0; i < diff; i++) tmpA = tmpA.next;
		else
			for (int i = 0; i < diff; i++) tmpB = tmpB.next;

		// move forward together
		while (tmpA != null && tmpB != null) {
			if (tmpA == tmpB) return tmpA;
			tmpA = tmpA.next;
			tmpB = tmpB.next;
		}

		return null;
	}

	// 如果存在cycle, return 交叉点, 不存在, return null
	private ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) return null;

		ListNode fast = head;
		ListNode slow = head;
		ListNode intersection = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			// fast重合，intersection从头开始追
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
