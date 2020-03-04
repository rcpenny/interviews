class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int add = 0;

    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;

    // add == 1的情况 [5] [5]
    while (l1 != null || l2 != null || add == 1) {
      int sum = add;
      if (l1 != null) sum += l1.val;
      if (l2 != null) sum += l2.val;

      ListNode node = new ListNode(sum % 10);

      tail.next = node;
      tail = node;

      add = sum / 10;

      if (l1 != null) l1 = l1.next;
      if (l2 != null) l2 = l2.next;
    }

    return dummy.next;
  }
}