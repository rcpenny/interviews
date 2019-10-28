// leet160  考虑两个list有没有环

public class IntersectionTwoLinkedList {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		// 查环
		ListNode aCycleIntersection = detectCycle(headA);
		ListNode bCycleIntersection = detectCycle(headB);

		// 均无环
		if (aCycleIntersection == null && bCycleIntersection == null) {
			return NoCycleIntersection(headA, headB);
		}

		// 都有环
		if (aCycleIntersection != null && bCycleIntersection != null) {
			if (aCycleIntersection != bCycleIntersection) {
				return aCycleIntersection; // return any of them
			}

			// 两个intersection相同，交点再cycle前面，用均无环的思想写
			return twoCyclesIntersection(headA, headB);
		} 

		// 一有一无 无交点
		return null;
	}

	private ListNode detectCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		ListNode intersection = head;

		while (fast != null && fast.next != null) {
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

	// headA与headB无环,解法2
	// 可以理解成两个人速度一致， 走过的路程一致。那么肯定会同一个时间点到达终点。
	// 如果到达终点的最后一段路两人都走的话，那么这段路上俩人肯定是肩并肩手牵手的。 nb
  private ListNode NoCycleIntersection(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;

		while (a != b) {
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}
		return a;
	}

  // headA与headB无环,解法2
  private ListNode NoCycleIntersection2(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;

		int aLength = 0;
		int bLength = 0;

		while (a != null) {
			a = a.next;
			aLength++;
		}

		while (b != null) {
			b = b.next;
			bLength++;
		}

		int diff = Math.abs(aLength - bLength);
		a = headA;
		b = headB;

		if (aLength >= bLength) {
			for (int i = 0; i < diff; i++) a = a.next;
		} else {
			for (int i = 0; i < diff; i++) b = b.next;
		}

		while (a != null && b != null) {
			if (a == b) return a;
			a = a.next;
			b = b.next;
		}

		return null;
	}

	// 都有环
	private ListNode twoCyclesIntersection(ListNode headA, ListNode headB, ListNode start) {
		// get length a to cycle start
		// get length b to cycle start
		// find intersction
	}
}
