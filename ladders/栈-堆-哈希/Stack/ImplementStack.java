import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// USE ARRAY LIST
public class ImplementStack {
	List<Integer> list;

	ImplementStack() {
		this.list = new ArrayList<>();
	}

	public void push(int x) {
		list.add(x);
	}

	public void pop() {
		list.remove(list.size() - 1);
	}

	public int top() {
		return list.get(list.size() - 1);
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}
}

// USE 2 QUEUE
class ImplementStack2 {

	// queue1 is main queue, queue2 is just for operations
	Queue<Integer> queue1;
	Queue<Integer> queue2;

	public ImplementStack2() {
		this.queue1 = new LinkedList<>();
		this.queue2 = new LinkedList<>();
	}

	public void push(int x) {
		queue1.offer(x);
	}

	public void pop() {
		moveItems(queue1, queue2);
		queue1.poll();
		swap();
	}

	public int top() {
		moveItems(queue1, queue2);
		int item = queue1.poll();
		swap();
		// 加回来
		queue1.offer(item);
		return item;
	}

	public boolean isEmpty() {
		return queue1.isEmpty() && queue2.isEmpty();
	}

	private void moveItems(Queue<Integer> queue1, Queue<Integer> queue2) {
		while (queue1.size() > 1) queue2.offer(queue1.poll());
	}

	private void swap() {
		Queue<Integer> tmp = this.queue1;
		this.queue1 = this.queue2;
		this.queue2 = tmp;
	}
}
