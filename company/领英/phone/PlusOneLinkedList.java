// 输入: 9 -> 9 -> null
// 输出: 1 -> 0 -> 0 -> null
// 解释:
// 99 + 1 = 100

public class PlusOneLinkedList {
  public ListNode plusOne(ListNode head) {
    ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		// 记录两个指针，其中r指的是当前位置，l的含义是当前最后一个不为9的数字
    ListNode l = dummy;
    ListNode r = dummy;
		
		// 当r遍历到null时，我们只需要让l的next节点到null的所有节点的值全都归零，然后将l对应的值+1即可
    while (r.next != null) {
      r = r.next;
      if (r.val != 9) l = r;
    }

    if (r.val != 9) {
      r.val++;
    } else {
      l.val++;
      l = l.next;
      while (l != null) {
          l.val = 0;
          l = l.next;
      }
    }
    
    if (dummy.val == 0) return dummy.next;
    return dummy;
  }
}
