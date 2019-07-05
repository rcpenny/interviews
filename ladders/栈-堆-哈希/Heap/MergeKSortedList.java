import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 合并k个排序链表，并且返回合并后的排序链表。尝试分析和描述其复杂度。

// Example
// 样例 1:
// 	输入:   [2->4->null,null,-1->null]
// 	输出:  -1->2->4->null

// 样例 2:
// 	输入: [2->6->null,5->null,7->null]
// 	输出:  2->5->6->7->null

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class MergeKSortedList {

	private Comparator<ListNode> cpt = new Comparator<ListNode>() {
		public int compare(ListNode a, ListNode b) {
			return a.val - b.val;
		}
	};

  public ListNode mergeKLists(List<ListNode> lists) {  
		if (lists == null) return null;
		
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), cpt);

		// add head listnode to heap
		for (ListNode cur : lists) if (cur != null) heap.offer(cur);

		// 操作生成List的方法
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;

		while (!heap.isEmpty()) {
			ListNode tmp = heap.poll();
			current.next = tmp;
			current = tmp;
	
			if (tmp.next == null) continue;
			heap.offer(tmp.next);
		}

		return dummy.next;
	}
}
