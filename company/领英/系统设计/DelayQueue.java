import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

public class DelayQueue<E extends Delayed> {
	private final transient ReentrantLock lock = new ReentrantLock();

	private final PriorityQueue<E> queue = new PriorityQueue<E>();

	/**
	 * Thread designated to wait for the element at the head of
	 * the queue.  This variant of the Leader-Follower pattern
	 * (http://www.cs.wustl.edu/~schmidt/POSA/POSA2/) serves to
	 * minimize unnecessary timed waiting.  When a thread becomes
	 * the leader, it waits only for the next delay to elapse, but
	 * other threads await indefinitely.  The leader thread must
	 * signal some other thread before returning from take() or
	 * poll(...), unless some other thread becomes leader in the
	 * interim.  Whenever the head of the queue is replaced with
	 * an element with an earlier expiration time, the leader
	 * field is invalidated by being reset to null, and some
	 * waiting thread, but not necessarily the current leader, is
	 * signalled.  So waiting threads must be prepared to acquire
	 * and lose leadership while waiting.
	 */
	// 线程leader的作用
	// leader这个成员有啥作用？DelayQueue的设计其实是一个Leader/Follower模式，
	// leader就是指向Leader线程的。该模式可以减少不必要的等待时间，
	// 当一个线程是Leader时，它只需要一个时间差； 其他Follower线程则无限等待。
	// 比如头节点任务还有5秒就要开始了，那么Leader线程会sleep 5秒，不需要傻傻地等待固定时间间隔。
	private Thread leader = null;

	/**
	 * Condition signalled when a newer element becomes available
	 * at the head of the queue to consume
	 * or a new thread may need to become leader.
	 */
	private final Condition available = lock.newCondition();

	public DelayQueue() {}

	/**
	 * Inserts the specified element into this delay queue.
	 *
	 * @param e the element to add
	 * @return {@code true}
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean offer(E e) {
		final ReentrantLock offerLock = this.lock;
		// acquire the lock
		offerLock.lock();
		try {
			queue.offer(e);
			// 如果第一个元素等于刚刚插入进去的元素，说明刚才队列是空的。
			// 现在队列里有了一个任务，那么就应该唤醒所有在等待的消费者线程，
			// 避免了PriorityBlockingQueue + 时间差的缺点
			// 将leader重置为null，这些消费者之间互相竞争，自然有一个会被选为leader
			if (queue.peek() == e) {
				leader = null;
				// Wakes up one waiting thread.
				available.signal();
			}
			return true;
		} finally {
			offerLock.unlock();
		}
	}

	/**
	 * Retrieves and removes the head of this queue, waiting if necessary
	 * until an element with an expired delay is available on this queue.
	 *
	 * @return the head of this queue
	 * @throws InterruptedException {@inheritDoc}
	 */
	public E take() throws InterruptedException {
		final ReentrantLock takeLock = this.lock;
		// Acquires the lock unless the current thread is interrupted.
		lock.lockInterruptibly();

		try {
			for (;;) {
				E first = queue.peek();
				if (first == null) {
					// Causes the current thread to wait until it is signalled or interrupted.
					available.await();
				} else {
					long delay = first.getDelay(MILLISECONDS);
					if (delay <= 0) return queue.poll();

					// 如果删除这行代码，会发生什么呢？假设现在有3个消费者线程，
					// 线程A进来获取first,然后进入 else 的 else ,设置了leader为当前线程A，并让A等待一段时间
					// 线程B进来获取first, 进入else的阻塞操作,然后无限期等待，这时线程B是持有first引用的
					// 线程A等待指定时间后被唤醒，获取对象成功，出队，这个对象理应被GC回收，但是它还被线程B持有着，GC链可达，所以不能回收这个first
					// 只要线程B无限期的睡眠，那么这个本该被回收的对象就不能被GC销毁掉，那么就会造成内存泄露
					first = null;
					
					// 想象一下有个多个消费者线程用take方法去取任务,内部先加锁,然后每个线程都去peek头节点。
					// 如果leader不为空说明已经有线程在取了，让当前消费者无限等待。
					if (leader != null) {
						available.await();
					} else {		
						// 如果为空说明没有其他消费者去取任务,设置leader为当前消费者，并让改消费者等待指定的时间
						// 下次循环会走如下分支，取到任务结束，if (delay <= 0) return q.poll();
						Thread thisThread = Thread.currentThread();
						leader = thisThread;

						try {
							available.await(delay, MILLISECONDS);
						} finally {
							if (leader == thisThread) leader = null;
						}
					}
				}
			}
		} finally {
			if (leader == null && queue.peek() != null) available.signal();
			takeLock.unlock();
		}
	}
}

/** Delayed Task, implements Delayed. */
class DelayTask implements Delayed {
	private Task task;
	private long duration;

	public DelayTask(Task task, long delay) {
		this.task = task;
		this.duration = System.currentTimeMillis() + duration;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = duration - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(DelayTask o) {
		return this.duration - o.duration;
	}
}

/** Producer of DelayTask */
// 生产者很简单，就是一个死循环，不断地产生一些是时间随机的任务。
class DelayTaskProducer implements Runnable {
	private final Random random = new Random();
	private DelayQueue<DelayTask> queue;

	public DelayTaskProducer(DelayQueue<DelayTask> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				int delay = random.nextInt(10000);
				DelayTask task = new DelayTask(new Task(), delay);
				queue.offer(task);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/** Consumer of DelayTask */
// 当 DelayQueue 里没有任务时，TaskConsumer会无限等待，直到被唤醒，因此它不会消耗CPU
class DelayTaskConsumer implements Runnable {
	private DelayQueue<DelayTask> queue;

	public DelayTaskConsumer(DelayQueue<DelayTask> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				DelayTask task = queue.take();
				System.out.println(task);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// 定时任务调度器
class TaskScheduler {
	public static void main(String[] args) {
		DelayQueue<DelayTask> queue = new DelayQueue<>();

		new Thread(new DelayTaskProducer(queue), "Producer thread").start();
		new Thread(new DelayTaskConsumer(queue), "Consumer thread").start();
	} 
}

/** Actual Task */
class Task {Task() {}}
