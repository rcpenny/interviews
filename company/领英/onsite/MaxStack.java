import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

// 解1 双栈
// Time: O(N) for the popMax operation, and O(1)O(1) for the other operations
// Space: O(N), the maximum size of the stack.
public class MaxStack {
	private Stack<Integer> realStack;
	private Stack<Integer> maxStack;
	
	public MaxStack() {
		this.realStack = new Stack<>();
		this.maxStack = new Stack<>();
	}
	
	public void push(int x) {
		realStack.push(x);
		if (maxStack.isEmpty()) 
			maxStack.push(x);
		else 
			maxStack.push(Math.max(peekMax(), x));
	}

	public int pop() {
		maxStack.pop();
		return realStack.pop();
	}
	
	public int top() {
		return realStack.peek();
	}
	
	public int peekMax() {
		return maxStack.peek();
	}
	
	public int popMax() {
		int cur_max = peekMax();
		Stack<Integer> tmp = new Stack<>();

		while (top() != cur_max) {
			tmp.push(pop());
		}
		pop();

		while (!tmp.isEmpty()) {
			push(tmp.pop());
		}
		return cur_max;
	}
}

// 解2 Double Linked List + TreeMap

// 本质逻辑: double linked list 模拟 stack, treemap对应stack中每个value和其相应的nodes

// Time: O(logN) for all operations except peek which is O(1)O(1)
// Most operations involving TreeMap are O(logN).

// Space: O(N), the size of the data structures used.

class MaxStack {
	TreeMap<Integer, List<Node>> map;
	DoubleLinkedList dll;

	public MaxStack() {
		this.map = new TreeMap();
		this.dll = new DoubleLinkedList();
	}

	public void push(int x) {
		Node node = dll.add(x);
		if(!map.containsKey(x))
			map.put(x, new ArrayList<Node>());

		map.get(x).add(node);
	}

	public int pop() {
		int val = dll.pop();
		List<Node> L = map.get(val);
		L.remove(L.size() - 1);
		if (L.isEmpty()) map.remove(val);
		return val;
	}

	public int top() {
		return dll.peek();
	}

	public int peekMax() {
		return map.lastKey();
	}

	public int popMax() {
		int max = peekMax();
		List<Node> L = map.get(max);
		Node node = L.remove(L.size() - 1);
		dll.unlink(node);
		if (L.isEmpty()) map.remove(max);
		return max;
	}
}

class DoubleLinkedList {
	// head & tail只是pointer，并不是真正的node
	Node head;
	Node tail;

	public DoubleLinkedList() {
			head = new Node(0);
			tail = new Node(0);
			head.next = tail;
			tail.prev = head;
	}

	// 加一个到DLL尾部
	public Node add(int val) {
			Node x = new Node(val);
			x.next = tail;
			x.prev = tail.prev;
			tail.prev = tail.prev.next = x;
			return x;
	}

	// 去除最后一个node
	public int pop() {
		return unlink(tail.prev).val;
	}

	// 返回最后一个node value
	public int peek() {
		return tail.prev.val;
	}

	// 从DLL中去掉node
	public Node unlink(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		return node;
	}
}

// DLL Node
class Node {
	int val;
	Node prev;
	Node next;
	public Node(int v) {
		val = v;
	}
}
