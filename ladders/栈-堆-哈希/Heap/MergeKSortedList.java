import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 合并k个排序链表，并且返回合并后的排序链表。尝试分析和描述其复杂度。
// 	输入:   [2->4->null,null,-1->null]
// 	输出:  -1->2->4->null

public class MergeKSortedList {

  private Comparator<ListNode> cpt = new Comparator<ListNode>() {
    @Override public int compare(ListNode a, ListNode b) {
      return a.val - b.val;
    }
  };

  public ListNode mergeKLists(List<ListNode> lists) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    PriorityQueue<ListNode> minheap = new PriorityQueue<ListNode>(cpt);
    
    for (ListNode head : lists) {
      if (head != null) minheap.offer(head);
    }
    
    while (!minheap.isEmpty()) {
      ListNode tmp = minheap.poll();
			tail.next = tmp;
			tail = tail.next;
      if (tmp.next != null) minheap.offer(tmp.next);      
    }
    
    return dummy.next;
  }
}
